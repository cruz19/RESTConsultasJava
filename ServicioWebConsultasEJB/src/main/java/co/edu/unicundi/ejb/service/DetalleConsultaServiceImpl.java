package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.DetalleConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IDetalleConsultaService;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import co.edu.unicundi.ejb.repository.IDetalleConsultaRepository;
import java.lang.reflect.Type;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class DetalleConsultaServiceImpl implements IDetalleConsultaService {
    
    ModelMapper modelMapper = new ModelMapper();
    
    @EJB
    private IDetalleConsultaRepository dcRepository;
    
    @EJB
    private IConsultaRepository consultaRepository;

    @Override
    public PagedListDto buscar(Integer pageNumber, Integer pageSize, boolean details) {
        // Listar
        List<DetalleConsulta> dcList = dcRepository.findAll(pageNumber, pageSize);
        // Mapper
        Type listType = new TypeToken<List<DetalleConsultaDto>>(){}.getType();
        List<DetalleConsultaDto> dcDTOList = modelMapper.map(dcList, listType);
        
        // Detalles
        if (details){
            for(DetalleConsultaDto dc : dcDTOList) { dc.getConsulta().setMedico(null); dc.getConsulta().setDetallesConsulta(null); }
        } else {
            for(DetalleConsultaDto dc : dcDTOList){ dc.setConsulta(null); }
        }
        
        // PagedList
        return new PagedListDto(dcDTOList, consultaRepository.count(), pageNumber, pageSize);
    }

    @Override
    public DetalleConsultaDto buscarPorId(Integer id, boolean details) throws ModelNotFoundException {
        DetalleConsulta dc = dcRepository.find(id);
        if (dc == null){
            throw new ModelNotFoundException("No existe una consulta con el id enviado");
        }
        DetalleConsultaDto dcDTO = modelMapper.map(dc, DetalleConsultaDto.class);
        
        // Detalles
        if (details){
            dcDTO.getConsulta().setMedico(null);
            dcDTO.getConsulta().setDetallesConsulta(null);
        }
        else
            dcDTO.setConsulta(null);
        
        return dcDTO;
    }

    @Override
    public void guardar(DetalleConsulta detalleConsulta) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        if (detalleConsulta == null){
            throw new EmptyModelException("El objeto detalle consulta está vacío");
        }
        if (detalleConsulta.getConsulta().getId() != null){
            Consulta consulta = consultaRepository.find(detalleConsulta.getConsulta().getId());
            if (consulta == null){
                throw new ModelNotFoundException("No existe una consulta con el id enviado");
            }
            detalleConsulta.setConsulta(consulta);
            consulta.getDetallesConsulta().add(detalleConsulta);
        } else {
            throw new IntegrityException("El id de la consulta es requerido");
        }
        
        dcRepository.create(detalleConsulta);
    }

    @Override
    public void actualizar(DetalleConsulta detalleConsulta) throws EmptyModelException, ModelNotFoundException {
        if (detalleConsulta == null){
            throw new EmptyModelException("El objeto detalle consulta está vacío");
        }
        if (detalleConsulta.getId() == null){
            throw new ModelNotFoundException("No existe un detalle consulta con el id enviado");
        }
        // Actualizar
        DetalleConsulta dcEntity = dcRepository.find(detalleConsulta.getId());
        if (dcEntity == null){
            throw new ModelNotFoundException("No existe un detalle consulta con el id enviado");
        }
        dcEntity.setDiagnostico(detalleConsulta.getDiagnostico());
        dcEntity.setTratamiento(detalleConsulta.getTratamiento());
        // Médico
        if (detalleConsulta.getConsulta().getId() != null){
            Consulta consulta = consultaRepository.find(detalleConsulta.getConsulta().getId());
            if (consulta == null){
                throw new ModelNotFoundException("No existe una consulta con el id enviado");
            }
            dcEntity.setConsulta(consulta);
        }
        dcRepository.edit(dcEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        DetalleConsulta dc = dcRepository.find(id);
        if (dc == null){
            throw new ModelNotFoundException("No existe un detalle consulta con el id enviado");
        }
        dcRepository.remove(dc);
    }
    
}
