package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Provider
public class NotAllowedExceptionFilter implements ExceptionMapper<NotAllowedException> {
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotAllowedException exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto("Method Not Allowed", formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                        .entity(error)
                        .build();
    }

}
