package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import io.jsonwebtoken.SignatureException;
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
public class SignatureExceptionFilter implements ExceptionMapper<SignatureException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(SignatureException exception) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String message = "La firma JWT no coincide con la firma calculada localmente. La validez de JWT no se puede afirmar y no se debe confiar.";
        ErrorDto error = new ErrorDto(message, formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(error)
                        .build();
    }
}
