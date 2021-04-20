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
        return repository.buscar();
    }

    @Override
    public Consulta buscarPorId(Integer id) throws ModelNotFoundException {
        Consulta consulta = repository.buscarPorId(id);
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
        repository.guardar(consulta);
    }

    @Override
    public void actualizar(Consulta consulta) throws EmptyModelException, ModelNotFoundException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if (consulta.getId() == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        Consulta consultaPorId = repository.buscarPorId(consulta.getId());
        if (consultaPorId == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        repository.actualizar(consulta);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Consulta consulta = repository.buscarPorId(id);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        repository.eliminar(consulta);
    }
    
}
