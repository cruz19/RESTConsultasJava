package co.edu.unicundi.serviciowebconsultas.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import co.edu.unicundi.ejb.exceptions.JwtTokenException;
import co.edu.unicundi.ejb.exceptions.PermissionsException;
import co.edu.unicundi.ejb.interfaces.IUsuarioService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Steven Cruz
 * @author Daniel Cruz
 */
@Provider
public class Interceptor implements ContainerRequestFilter {
    
    @EJB
    private IUsuarioService usuarioService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String url = requestContext.getUriInfo().getAbsolutePath().toString();
        // Omitir login y registro
        if(!url.contains("/login") && !url.contains("/usuarios/guardar")) {
            String token = requestContext.getHeaderString("Authorization");
            if (token == null){
                ErrorDto error = new ErrorDto("El token es requerido", formatter.format(new Date()), url);
                requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity(error).build()
                );
            } else {
                try {
                    if (url.contains("/logout")){
                        usuarioService.logout(token);
                    } else {
                        // Validar token
                        usuarioService.validarToken(token);
                        // Validar permisos
                        usuarioService.validarPermisos(token, url);
                    }
                } catch (JwtTokenException ex) {
                    ErrorDto error = new ErrorDto(ex.getMessage(), formatter.format(new Date()), url);
                    requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity(error).build()
                    );
                } catch (PermissionsException ex) {
                    ErrorDto error = new ErrorDto(ex.getMessage(), formatter.format(new Date()), url);
                    requestContext.abortWith(
                        Response.status(Response.Status.FORBIDDEN).type(MediaType.APPLICATION_JSON).entity(error).build()
                    );
                }
            }
        }
    }
}

