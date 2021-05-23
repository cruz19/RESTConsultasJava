package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.ExamenDto;
import co.edu.unicundi.ejb.dtos.ExamenesConsultaDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.entity.ConsultaExamenPK;
import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaExamenService;
import co.edu.unicundi.ejb.repository.IConsultaExamenRepository;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import co.edu.unicundi.ejb.repository.IExamenRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
    @EJB
    private IExamenRepository examenRepository;
    
    @EJB
    private IConsultaRepository consultaRepository;
    
    @EJB
    private IConsultaExamenRepository consultaExamenRepository;
    
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void guardar(ConsultaExamen consultaExamen) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        if (consultaExamen == null){
            throw new EmptyModelException("El objeto está vacío");
        }
        // Consulta
        if (consultaExamen.getConsulta().getId() != null){
            Consulta consulta = consultaRepository.find(consultaExamen.getConsulta().getId());
            if (consulta == null){
                throw new ModelNotFoundException("No existe una consulta con el id enviado");
            }
            consultaExamen.setConsulta(consulta);
        } else {
            throw new IntegrityException("El id de la consulta es requerido");
        }
        // Examen
        if (consultaExamen.getExamen().getId() != null){
            Examen examen = examenRepository.find(consultaExamen.getExamen().getId());
            if (examen == null){
                throw new ModelNotFoundException("No existe un examen con el id enviado");
            }
            consultaExamen.setExamen(examen);
        } else {
            throw new IntegrityException("El id del examen es requerido");
        }
        
        consultaExamenRepository.create(consultaExamen);
    }

    @Override
    public void eliminar(ConsultaExamenPK id) throws ModelNotFoundException {
        ConsultaExamen consultaExamen = consultaExamenRepository.find(id);
        if (consultaExamen == null){
            throw new ModelNotFoundException("No existe un registro con el id enviado");
        }
        consultaExamenRepository.remove(consultaExamen);
    }

    @Override
    public ExamenesConsultaDto buscarPorConsulta(Integer idConsulta) throws ModelNotFoundException {
        Consulta consulta = consultaRepository.find(idConsulta);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        // Listar
        List<ConsultaExamen> consultaExamenList = consultaExamenRepository.findByConsulta(idConsulta);
        // Examenes consulta Dto
        ExamenesConsultaDto examenesConsulta = new ExamenesConsultaDto();
        examenesConsulta.setConsulta(modelMapper.map(consulta, ConsultaDto.class));
        for (ConsultaExamen ce : consultaExamenList){
            examenesConsulta.getExamenes().add(modelMapper.map(ce.getExamen(), ExamenDto.class));
        }
        examenesConsulta.getConsulta().setDetallesConsulta(null);
        examenesConsulta.getConsulta().setMedico(null);
        
        return examenesConsulta;
    }
    
}
