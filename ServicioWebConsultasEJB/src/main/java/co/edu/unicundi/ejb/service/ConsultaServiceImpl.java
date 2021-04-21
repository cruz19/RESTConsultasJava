package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaService;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class ConsultaServiceImpl implements IConsultaService {
    
    @EJB
    private IConsultaRepository repository;

    @Override
    public List<Consulta> buscar() {
        return repository.findAll();
    }

    @Override
    public Consulta buscarPorId(Integer id) throws ModelNotFoundException {
        Consulta consulta = repository.find(id);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        return consulta;
    }

    @Override
    public void guardar(Consulta consulta) throws EmptyModelException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        consulta.getMedico().getDireccion().setMedico(consulta.getMedico());
        repository.create(consulta);
    }

    @Override
    public void actualizar(Consulta consulta) throws EmptyModelException, ModelNotFoundException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if (consulta.getId() == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        Consulta consultaEntity = this.buscarPorId(consulta.getId());
        if (consultaEntity == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        // Actualizar consulta
        consultaEntity.setFecha(consulta.getFecha());
        // Detalles consulta
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consulta);
            }
            consultaEntity.setDetallesConsulta(consulta.getDetallesConsulta());
        }
        // Médico
        if (consulta.getMedico() != null){
            consultaEntity.setMedico(consulta.getMedico());
            // Dirección
            if (consulta.getMedico().getDireccion() != null){
                consultaEntity.getMedico().setDireccion(consulta.getMedico().getDireccion());
                consultaEntity.getMedico().getDireccion().setMedico(consultaEntity.getMedico());
            }
        }
        repository.edit(consultaEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Consulta consulta = this.buscarPorId(id);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        repository.remove(consulta);
    }
    
}
