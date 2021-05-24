package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.ExamenDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IExamenService;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/examenes")
public class ExamenesController {
    @EJB
    private IExamenService examenService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(
        @QueryParam("pagina") Integer pagina,
        @QueryParam("tamano") Integer tamano
    ) {
        PagedListDto examenes = examenService.buscar(pagina, tamano);
        return Response
                .status(Response.Status.OK)
                .entity(examenes)
                .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(
        @PathParam("id") int id
    ) throws ModelNotFoundException{
        ExamenDto examen = examenService.buscarPorId(id);
        return Response
                .status(Response.Status.OK)
                .entity(examen)
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid Examen examen) throws EmptyModelException{
        examenService.guardar(examen);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@Valid Examen examen) throws EmptyModelException, ModelNotFoundException{
        examenService.actualizar(examen);
        return Response
           .status(Response.Status.OK)
           .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) throws ModelNotFoundException{
        examenService.eliminar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
