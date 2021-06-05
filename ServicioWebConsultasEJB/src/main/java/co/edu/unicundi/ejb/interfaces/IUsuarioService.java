package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.dtos.TokenDto;
import co.edu.unicundi.ejb.entity.Usuario;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.JwtTokenException;
import co.edu.unicundi.ejb.exceptions.LoginException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.exceptions.PermissionsException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IUsuarioService {
    public PagedListDto buscar(Integer pageNumber, Integer pageSize);
    public void guardar(Usuario usuario) throws EmptyModelException, IntegrityException;
    public void cambiarEstado(Integer usuarioId, Boolean estado) throws ModelNotFoundException;
    public TokenDto login(String username, String contrasena) throws LoginException;
    public void validarToken(String token) throws JwtTokenException;
    public void validarPermisos(String jwt, String url) throws PermissionsException;
    public void logout(String token) throws JwtTokenException;
}


