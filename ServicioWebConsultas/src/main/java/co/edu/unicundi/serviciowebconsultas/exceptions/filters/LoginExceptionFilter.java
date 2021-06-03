package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import co.edu.unicundi.ejb.exceptions.LoginException;
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
public class LoginExceptionFilter implements ExceptionMapper<LoginException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(LoginException exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto(exception.getMessage(), formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(error)
                        .build();
    }
}
