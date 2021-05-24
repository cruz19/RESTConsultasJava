package co.edu.unicundi.serviciowebconsultas.controllers;

import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaExamenService;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 */
@Path("/consultas-examenes")
public class ConsultasExamenesController {
    @EJB
    private IConsultaExamenService consultaExamenService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(@Valid ConsultaExamen consultaExamen) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        consultaExamenService.guardar(consultaExamen);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    
    @DELETE
    @Path("{idConsulta}/{idExamen}")
    public Response eliminar(
        @PathParam("idConsulta") Integer idConsulta,
        @PathParam("idExamen") Integer idExamen
    ) throws ModelNotFoundException{
        consultaExamenService.eliminar(idConsulta, idExamen);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
