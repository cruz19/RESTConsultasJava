package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.interfaces.IMedicoViewService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/medicos_view")
public class MedicosViewController {
    @EJB
    private IMedicoViewService medicoViewService;
    
    @GET
    @Path("{pageNumber}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@PathParam("pageNumber") int pageNumber, @PathParam("pageSize") int pageSize) {
        PagedListDto consultas = medicoViewService.buscar(pageNumber, pageSize);
        return Response
                .status(Response.Status.OK)
                .entity(consultas)
                .build();
    }
}
