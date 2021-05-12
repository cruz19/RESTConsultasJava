package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaService;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
import java.lang.reflect.Type;
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
public class ConsultaServiceImpl implements IConsultaService {
    
    @EJB
    private IConsultaRepository consultaRepository;
    @EJB
    private IMedicoRepository medicoRepository;
    
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public PagedListDto buscar(int pageNumber, int pageSize) {
        // Listar
        List<Consulta> consultaList = consultaRepository.findAll(pageNumber, pageSize);
        // Mapper
        Type listType = new TypeToken<List<ConsultaDto>>(){}.getType();
        List<ConsultaDto> consultaDtoList = modelMapper.map(consultaList, listType);
        for(ConsultaDto c : consultaDtoList){ c.getMedico().setConsultas(null); }
        // PagedList
        return new PagedListDto(consultaDtoList, consultaRepository.count(), pageNumber, pageSize);
    }

    @Override
    public ConsultaDto buscarPorId(Integer id) throws ModelNotFoundException {
        Consulta consulta = consultaRepository.find(id);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        ConsultaDto consultaDTO = modelMapper.map(consulta, ConsultaDto.class);
        consultaDTO.getMedico().setConsultas(null);
        return consultaDTO;
    }

    @Override
    public void guardar(Consulta consulta) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        if (consulta.getMedico().getId() != null){
            Medico medico = medicoRepository.find(consulta.getMedico().getId());
            if (medico == null){
                throw new ModelNotFoundException("No existe un médico con el id enviado");
            }
            consulta.setMedico(medico);
            medico.getConsultas().add(consulta);
        }
        consultaRepository.create(consulta);
    }

    @Override
    public void actualizar(Consulta consulta) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if (consulta.getId() == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        Consulta consultaEntity = consultaRepository.find(consulta.getId());
        if (consultaEntity == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        // Actualizar consulta
        consultaEntity.setFecha(consulta.getFecha());
        // Detalles consulta
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consultaEntity);
            }
            consultaEntity.setDetallesConsulta(consulta.getDetallesConsulta());
        }
        // Médico
        if (consulta.getMedico().getId() != null){
            Medico medico = medicoRepository.find(consulta.getMedico().getId());
            if (medico == null){
                throw new ModelNotFoundException("No existe un médico con el id enviado");
            }
            consultaEntity.setMedico(medico);
        }
        consultaRepository.edit(consultaEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Consulta consulta = consultaRepository.find(id);
        if (consulta == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        consultaRepository.remove(consulta);
    }
    
}
