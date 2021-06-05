package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.TokenDto;
import co.edu.unicundi.ejb.exceptions.LoginException;
import co.edu.unicundi.ejb.interfaces.IUsuarioService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/login")
public class LoginController {
    @EJB
    private IUsuarioService usuarioService;
    
    @GET
    @Path("{username}/{contrasena}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
        @PathParam("username") String username,
        @PathParam("contrasena") String contrasena) throws LoginException
    {
        TokenDto token = usuarioService.login(username, contrasena);
        return Response.status(Response.Status.OK)
                .entity(token)
                .build();
    }
}

