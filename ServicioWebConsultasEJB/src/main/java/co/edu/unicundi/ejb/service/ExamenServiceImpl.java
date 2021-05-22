package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.DetalleConsultaDto;
import co.edu.unicundi.ejb.dtos.ExamenDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IExamenService;
import co.edu.unicundi.ejb.repository.IExamenRepository;
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
public class ExamenServiceImpl implements IExamenService {
    
    @EJB
    private IExamenRepository repository;
    
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public PagedListDto buscar(Integer pageNumber, Integer pageSize) {
        // Listar
        List<Examen> examenList = repository.findAll(pageNumber, pageSize);
        // Mapper
        Type listType = new TypeToken<List<ExamenDto>>(){}.getType();
        List<ExamenDto> examenDtoList = modelMapper.map(examenList, listType);
        
        // PagedList
        return new PagedListDto(examenDtoList, repository.count(), pageNumber, pageSize);
    }

    @Override
    public ExamenDto buscarPorId(Integer id) throws ModelNotFoundException {
        Examen examen = repository.find(id);
        if (examen == null){
            throw new ModelNotFoundException("No existe un examen con el id enviado");
        }
        ExamenDto examenDTO = modelMapper.map(examen, ExamenDto.class);
        return examenDTO;
    }

    @Override
    public void guardar(Examen examen) throws EmptyModelException {
        if (examen == null){
            throw new EmptyModelException("El objeto examen está vacío");
        }
        repository.create(examen);
    }

    @Override
    public void actualizar(Examen examen) throws EmptyModelException, ModelNotFoundException {
        if (examen == null){
            throw new EmptyModelException("El objeto examen está vacío");
        }
        if (examen.getId() == null){
            throw new ModelNotFoundException("No existe un examen con el id enviado");
        }
        Examen examenEntity = repository.find(examen.getId());
        if (examenEntity == null){
            throw new ModelNotFoundException("No existe un examen con el id enviado");
        }
        // Actualizar examen
        examenEntity.setNombre(examen.getNombre());
        examenEntity.setDescripcion(examen.getDescripcion());

        repository.edit(examenEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Examen examen = repository.find(id);
        if (examen == null){
            throw new ModelNotFoundException("No existe un examen con el id enviado");
        }
        repository.remove(examen);
    }
    
}
