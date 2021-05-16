package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.MedicoDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IMedicoService;
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
@Path("/medicos")
public class MedicosController {
    @EJB
    private IMedicoService medicoService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(
        @QueryParam("pageNumber") Integer pageNumber,
        @QueryParam("pageSize") Integer pageSize,
        @QueryParam("details") boolean details
    ) {
        PagedListDto consultas = medicoService.buscar(pageNumber, pageSize, details);
        return Response
                .status(Response.Status.OK)
                .entity(consultas)
                .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(
        @PathParam("id") int id,
        @QueryParam("details") boolean details
    ) throws ModelNotFoundException{
        MedicoDto medico = medicoService.buscarPorId(id, details);
        return Response
                .status(Response.Status.OK)
                .entity(medico)
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid Medico medico) throws EmptyModelException, IntegrityException{
        medicoService.guardar(medico);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@Valid Medico medico) throws EmptyModelException, ModelNotFoundException, IntegrityException{
        medicoService.actualizar(medico);
        return Response
           .status(Response.Status.OK)
           .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) throws ModelNotFoundException{
        medicoService.eliminar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
