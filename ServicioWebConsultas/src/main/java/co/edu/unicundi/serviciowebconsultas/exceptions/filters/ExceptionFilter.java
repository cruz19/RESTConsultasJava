package co.edu.unicundi.serviciowebconsultas.exceptions.filters;

import co.edu.unicundi.ejb.dtos.ErrorDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
        Throwable t = exception.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (t instanceof ConstraintViolationException) {
            List<String> validations = getValidations((ConstraintViolationException)t);
            ErrorDto error = new ErrorDto(validations, formatter.format(new Date()), request.getRequestURI());
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
        } else {
            ErrorDto error = new ErrorDto("Ha ocurrido un error", formatter.format(new Date()), request.getRequestURI());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(error)
                        .build();
        }
    }
    
    private List<String> getValidations(ConstraintViolationException exception){
        List<String> validations = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            validations.add(violation.getMessage());
        }
        return validations;
    }
}
