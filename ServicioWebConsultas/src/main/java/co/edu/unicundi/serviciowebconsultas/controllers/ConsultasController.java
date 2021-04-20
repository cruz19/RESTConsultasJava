package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaService;
import java.util.List;
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        List<Consulta> consultas = consultaService.buscar();
        return Response
                .status(Response.Status.OK)
                .entity(consultas)
                .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) throws ModelNotFoundException{
        Consulta consulta = consultaService.buscarPorId(id);
        return Response
                .status(Response.Status.OK)
                .entity(consulta)
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid Consulta consulta) throws EmptyModelException{
        System.out.println("Fecha: " + consulta.getFecha());
        consultaService.guardar(consulta);
        return Response
            .status(Response.Status.CREATED)
            .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@Valid Consulta consulta) throws EmptyModelException, ModelNotFoundException{
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
}