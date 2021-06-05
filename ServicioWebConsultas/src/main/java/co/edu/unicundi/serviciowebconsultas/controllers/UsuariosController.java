package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Usuario;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IUsuarioService;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/usuarios")
public class UsuariosController {
    @EJB
    private IUsuarioService usuarioService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(
        @QueryParam("pagina") Integer pagina,
        @QueryParam("tamano") Integer tamano
    ) {
        PagedListDto usuarios = usuarioService.buscar(pagina, tamano);
        return Response
                .status(Response.Status.OK)
                .entity(usuarios)
                .build();
    }
    
    @POST
    @Path("guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid Usuario usuario) throws EmptyModelException, IntegrityException{
        usuarioService.guardar(usuario);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @GET
    @Path("cambiarEstado/{id}/{estado}")
    public Response cambiarEstado(
        @PathParam("id") Integer id,
        @PathParam("estado") Boolean estado) throws ModelNotFoundException
    {
        usuarioService.cambiarEstado(id, estado);
        return Response.status(Response.Status.OK)
                .build();
    }
}
