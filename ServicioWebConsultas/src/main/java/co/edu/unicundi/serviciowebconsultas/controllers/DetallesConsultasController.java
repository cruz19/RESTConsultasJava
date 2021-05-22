package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.DetalleConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IDetalleConsultaService;
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
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Path("/detallesConsultas")
public class DetallesConsultasController {
    @EJB
    private IDetalleConsultaService dcService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(
            @QueryParam("pageNumber") Integer pageNumber,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("details") boolean details
    ) {
        PagedListDto detallesConsultas = dcService.buscar(pageNumber, pageSize, details);
        return Response
                .status(Response.Status.OK)
                .entity(detallesConsultas)
                .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(
        @PathParam("id") int id,
        @QueryParam("details") boolean details
    ) throws ModelNotFoundException{
        DetalleConsultaDto detalleConsulta = dcService.buscarPorId(id, details);
        return Response
                .status(Response.Status.OK)
                .entity(detalleConsulta)
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid DetalleConsulta detalleConsulta) throws EmptyModelException, IntegrityException, ModelNotFoundException{
        dcService.guardar(detalleConsulta);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@Valid DetalleConsulta detalleConsulta) throws EmptyModelException, ModelNotFoundException, IntegrityException{
        dcService.actualizar(detalleConsulta);
        return Response
           .status(Response.Status.OK)
           .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) throws ModelNotFoundException{
        dcService.eliminar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
