package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Steven Cruz
 */
@Provider
public class NotFoundExceptionFilter implements ExceptionMapper<NotFoundException> {
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotFoundException exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto("Not Found Exception", formatter.format(new Date()), request.getRequestURI());       
        return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
    }
    
}
