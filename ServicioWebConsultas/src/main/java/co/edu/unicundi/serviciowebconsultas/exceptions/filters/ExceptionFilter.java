package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Provider
public class ExceptionFilter implements ExceptionMapper<Exception> {
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(Exception exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto("Ha ocurrido un error", formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(error)
                        .build();
    }
    
}
