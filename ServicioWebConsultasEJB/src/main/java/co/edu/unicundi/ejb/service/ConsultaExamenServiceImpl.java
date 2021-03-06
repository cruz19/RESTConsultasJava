package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.ConsultaExamenDto;
import co.edu.unicundi.ejb.dtos.ExamenInfoDto;
import co.edu.unicundi.ejb.dtos.ExamenesConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaExamenService;
import co.edu.unicundi.ejb.repository.IConsultaExamenRepository;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import co.edu.unicundi.ejb.repository.IExamenRepository;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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
        if (consultaExamen.getConsulta() == null || consultaExamen.getConsulta().getId() == null){
            throw new EmptyModelException("La consulta es requerida");
        }
        Consulta consulta = consultaRepository.find(consultaExamen.getConsulta().getId());
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        consultaExamen.setConsulta(consulta);

        // Examen
        if (consultaExamen.getExamen() == null || consultaExamen.getExamen().getId() == null){
            throw new EmptyModelException("El examen es requerido");
        }
        Examen examen = examenRepository.find(consultaExamen.getExamen().getId());
        if (examen == null){
            throw new ModelNotFoundException("No existe un examen con el id enviado");
        }
        consultaExamen.setExamen(examen);
        // Verificar si la relación ya existe
        boolean exists = consultaExamenRepository
                        .findByPK(consultaExamen.getConsulta().getId(), consultaExamen.getExamen().getId());
        if (exists){
            throw new IntegrityException("La relación consulta-examen ya ha existe");
        }
        consultaExamenRepository.create(consultaExamen);
    }

    @Override
    public void eliminar(Integer idConsulta, Integer idExamen) throws ModelNotFoundException {
        boolean exists = consultaExamenRepository.findByPK(idConsulta, idExamen);
        if (!exists){
            throw new ModelNotFoundException("No existe un registro con la relación consulta-examen enviada");
        }
        consultaExamenRepository.removeByPK(idConsulta, idExamen);
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

        List<ExamenInfoDto> examenes = new ArrayList<>();
        for (ConsultaExamen ce : consultaExamenList){
            ExamenInfoDto examen = modelMapper.map(ce.getExamen(), ExamenInfoDto.class);
            examen.setInfoAdicional(ce.getInfoAdicional());
            examenes.add(examen);
        }
        examenesConsulta.setExamenes(examenes);
        examenesConsulta.getConsulta().setDetallesConsulta(null);
        examenesConsulta.getConsulta().setMedico(null);
        
        return examenesConsulta;
    }

    @Override
    public PagedListDto buscar(Integer pagina, Integer tamano) {
        // Listar
        List<ConsultaExamen> list = consultaExamenRepository.findAll(pagina, tamano);
        // Mapper
        Type listType = new TypeToken<List<ConsultaExamenDto>>(){}.getType();
        List<ConsultaExamenDto> listDto = modelMapper.map(list, listType);
        
        for(ConsultaExamenDto c : listDto){
            c.getConsulta().setMedico(null);
            c.getConsulta().setDetallesConsulta(null);
        }
        // PagedList
        return new PagedListDto(listDto, consultaExamenRepository.count(), pagina, tamano);
    }
    
}
