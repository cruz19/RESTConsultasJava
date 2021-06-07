package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.dtos.TokenDto;
import co.edu.unicundi.ejb.dtos.UsuarioDto;
import co.edu.unicundi.ejb.entity.Rol;
import co.edu.unicundi.ejb.entity.Usuario;
import co.edu.unicundi.ejb.entity.Token;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.JwtTokenException;
import co.edu.unicundi.ejb.exceptions.LoginException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.exceptions.PermissionsException;
import co.edu.unicundi.ejb.interfaces.IUsuarioService;
import co.edu.unicundi.ejb.repository.IRolRepository;
import co.edu.unicundi.ejb.repository.ITokenRepository;
import co.edu.unicundi.ejb.repository.IUsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class UsuarioService implements IUsuarioService {
    
    private final String SECRET_KEY = "qWbBaJb5FX";
    
    @EJB
    private IUsuarioRepository usuarioRepository;
    
    @EJB
    private ITokenRepository tokenRepository;
    
    @EJB
    private IRolRepository rolRepository;
    
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public TokenDto login(String username, String contrasena) throws LoginException {
        contrasena = this.getSHA512(contrasena);
        Usuario usuario = usuarioRepository.login(username, contrasena);
        if (usuario != null){
            
            // Verificar si el usuario está habilitado o no
            if (!usuario.getEstado()){
                throw new LoginException("El usuario está deshabilitado");
            }
            
            long tiempoActual = System.currentTimeMillis();
            
            Map<String, Object> permisos = new HashMap<>();
            if (usuario.getRol().getId() == 1){ // Administrador
                permisos.put("1", "/medicos");
                permisos.put("2", "/usuarios");
            } else if (usuario.getRol().getId() == 2){ // Médico
                permisos.put("1", "/consultas");
                permisos.put("2", "/examenes");
            }
            
            String jwt = Jwts.builder()
                             .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                             .setSubject(usuario.getUsername())
                             .setIssuedAt(new Date(tiempoActual))
                             .setExpiration(new Date(tiempoActual + 1800000)) // 30 min
                             .claim("usuario_id", usuario.getId())
                             .claim("permisos", permisos)
                             .compact();
            
            // Agregar registro de token
            Token token = new Token();
            token.setJwtToken(jwt);
            token.setUsuario(usuario);
            tokenRepository.create(token);
            // Retornar DTO
            TokenDto dto = new TokenDto();
            dto.setToken(jwt);
            return dto;
        } else {
            throw new LoginException("Usuario y/o contraseña incorrecta");
        }
    }
    
    // Decodificar token con llave y validar
    @Override
    public void validarToken(String jwt) throws JwtTokenException {
        // Obtener los claims
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(jwt).getBody();

        // Buscar solo token
        boolean existeToken = tokenRepository.findByToken(jwt);
        if (!existeToken){
            throw new JwtTokenException("No se ha podido encontrar el token enviado");
        }
        // Buscar token y usuario
        int usuarioId = claims.get("usuario_id", Integer.class);
        boolean existe = tokenRepository.findByTokenAndUser(jwt, usuarioId);
        if (!existe){
            throw new JwtTokenException("El token no pertenece al usuario");
        }
    }
    
    @Override
    public void validarPermisos(String jwt, String url) throws PermissionsException {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        HashMap<String, Object> permisos = claims.get("permisos", HashMap.class);
        boolean url_valida = false;
        for (String clave : permisos.keySet()) {
            String permiso = (String) permisos.get(clave);
            if (url.contains(permiso)){
                url_valida = true;
                return;
            }
        }
        if (!url_valida){
            throw new PermissionsException("El usuario no tiene permisos sobre esta tabla");
        }
    }

    @Override
    public PagedListDto buscar(Integer pagina, Integer tamano) {
                // Listar
        List<Usuario> lista = usuarioRepository.findAll(pagina, tamano);
        // Mapper
        Type listType = new TypeToken<List<UsuarioDto>>(){}.getType();
        List<UsuarioDto> listaDto = modelMapper.map(lista, listType);
        
        for(UsuarioDto dto : listaDto) dto.setContrasena(null);
        
        // PagedList
        return new PagedListDto(listaDto, usuarioRepository.count(), pagina, tamano);
    }

    @Override
    public void guardar(Usuario usuario) throws EmptyModelException, IntegrityException {
        if (usuario == null){
            throw new EmptyModelException("El objeto usuario está vacío");
        }
        boolean existeCorreo = usuarioRepository.findByEmail(usuario.getCorreo());
        if (existeCorreo){
            throw new IntegrityException("Ya existe un usuario con el correo enviado");
        }
        boolean existeUsername = usuarioRepository.findByUsername(usuario.getUsername());
        if (existeUsername){
            throw new IntegrityException("Ya existe un usuario con el username enviado");
        }
        if (usuario.getRol().getId() == null){
            throw new EmptyModelException("El id del rol es requerido");
        }
        Rol rol = rolRepository.find(usuario.getRol().getId());
        if (rol == null){
            throw new IntegrityException("No existe un rol con el id enviado");
        }
        // Contraseña
        usuario.setContrasena(this.getSHA512(usuario.getContrasena()));
        // Estado (por defecto habilitado)
        usuario.setEstado(true);
        usuarioRepository.create(usuario);
    }
    
    @Override
    public void cambiarEstado(Integer id, Boolean estado) throws ModelNotFoundException {
        Usuario usuario = usuarioRepository.find(id);
        if (usuario == null){
            throw new ModelNotFoundException("No existe un usuario con el id enviado");
        }
        usuario.setEstado(estado);
        // Actualizar estado del usuario
        usuarioRepository.edit(usuario);
    }
    
    @Override
    public void logout(String jwt) throws JwtTokenException {
        boolean existeToken = tokenRepository.findByToken(jwt);
        if (!existeToken){
            throw new JwtTokenException("No se ha podido encontrar el token enviado");
        }
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(jwt).getBody();
        int usuarioId = claims.get("usuario_id", Integer.class);
        // Eliminar token
        tokenRepository.removeToken(jwt, usuarioId);
    }
    
    /**
     * Encargado de generar SHA512 hash
     * @param input
     * @return 
     */
    private String getSHA512(String input) {
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    digest.reset();
	    digest.update(input.getBytes("utf8"));
	    return String.format("%0128x", new BigInteger(1, digest.digest()));
	} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
	    return null;
	}
    }
    
}
