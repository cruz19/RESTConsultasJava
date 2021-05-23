package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.ExamenesConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaExamenService;
import co.edu.unicundi.ejb.interfaces.IConsultaService;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/consultas")
public class ConsultasController {
    
    @EJB
    private IConsultaService consultaService;
    
    @EJB
    private IConsultaExamenService consultaExamenService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(
            @QueryParam("pageNumber") Integer pageNumber,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("details") boolean details
    ) {
        PagedListDto consultas = consultaService.buscar(pageNumber, pageSize, details);
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
        ConsultaDto consulta = consultaService.buscarPorId(id, details);
        return Response
                .status(Response.Status.OK)
                .entity(consulta)
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid Consulta consulta) throws EmptyModelException, IntegrityException, ModelNotFoundException{
        consultaService.guardar(consulta);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@Valid Consulta consulta) throws EmptyModelException, ModelNotFoundException, IntegrityException{
        consultaService.actualizar(consulta);
        return Response
           .status(Response.Status.OK)
           .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) throws ModelNotFoundException{
        consultaService.eliminar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
    @GET
    @Path("{id}/examenes")
    public Response buscarExamenes(@PathParam("id") int id) throws ModelNotFoundException {
        ExamenesConsultaDto examenesConsulta = consultaExamenService.buscarPorConsulta(id);
        return Response
                .status(Response.Status.OK)
                .entity(examenesConsulta)
                .build();
    }
    
    @POST
    @Path("ce/guardar")
    public Response agregarExamenes(@Valid ConsultaExamen consultaExamen) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        consultaExamenService.guardar(consultaExamen);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
}