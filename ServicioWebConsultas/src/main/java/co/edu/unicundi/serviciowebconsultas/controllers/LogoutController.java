package co.edu.unicundi.serviciowebconsultas.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/logout")
public class LogoutController {    
    @GET
    public Response logout()
    {
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }
}
