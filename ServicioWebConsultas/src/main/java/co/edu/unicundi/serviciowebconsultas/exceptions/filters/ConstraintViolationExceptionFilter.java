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
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Provider
public class ConstraintViolationExceptionFilter implements ExceptionMapper<ConstraintViolationException> {
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> validations = getValidations(exception);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErrorDto error = new ErrorDto(validations, formatter.format(new Date()), request.getRequestURI());
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(error)
                        .build();
    }
    
    private List<String> getValidations(ConstraintViolationException exception){
        List<String> validations = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            validations.add(violation.getMessage());
        }
        return validations;
    }
    
}
