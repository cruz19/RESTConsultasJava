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
    }
}

