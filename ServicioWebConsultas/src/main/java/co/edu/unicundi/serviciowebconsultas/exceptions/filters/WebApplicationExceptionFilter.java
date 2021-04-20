package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
  * @author Steven Cruz
 */
@Provider
public class WebApplicationExceptionFilter implements ExceptionMapper<WebApplicationException> {
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(WebApplicationException exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto("El objeto JSON está mal formado", formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(error)
                        .build();
    }
    
}
