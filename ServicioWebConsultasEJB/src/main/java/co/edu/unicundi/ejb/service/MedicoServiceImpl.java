package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.MedicoDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IMedicoService;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
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
public class MedicoServiceImpl implements IMedicoService {
    
    @EJB
    private IMedicoRepository repository;
    
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public PagedListDto buscar(Integer pageNumber, Integer pageSize, boolean details) {
        // Listar
        List<Medico> medicoList = repository.findAll(pageNumber, pageSize);
        // Mapper
        Type listType = new TypeToken<List<MedicoDto>>(){}.getType();
        List<MedicoDto> medicoDtoList = modelMapper.map(medicoList, listType);
        
        // Detalles
        if (details){
            for(MedicoDto medico : medicoDtoList)
                for(ConsultaDto consulta : medico.getConsultas()){ consulta.setMedico(null); consulta.setDetallesConsulta(null); }
        } else {
            for(MedicoDto medico : medicoDtoList) { medico.setConsultas(null); medico.setDireccion(null); }
        }
        
        // PagedList
        return new PagedListDto(medicoDtoList, repository.count(), pageNumber, pageSize);
    }

    @Override
    public MedicoDto buscarPorId(Integer id, boolean details) throws ModelNotFoundException {
        Medico medico = repository.find(id);
        if (medico == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        MedicoDto medicoDTO = modelMapper.map(medico, MedicoDto.class);
        if (details){
            for(ConsultaDto c : medicoDTO.getConsultas()){ c.setMedico(null); c.setDetallesConsulta(null); }
        } else {
            medicoDTO.setConsultas(null);
            medicoDTO.setDireccion(null);
        }
        return medicoDTO;
    }

    @Override
    public void guardar(Medico medico) throws EmptyModelException, IntegrityException {
        if (medico == null){
            throw new EmptyModelException("El objeto médico está vacío");
        }
        boolean emailExists = repository.findByEmail(medico.getCorreo(), -1);
        if (emailExists){
            throw new IntegrityException("Ya existe un médico con el correo enviado");
        }
        medico.getDireccion().setMedico(medico);
        repository.create(medico);
    }

    @Override
    public void actualizar(Medico medico) throws EmptyModelException, ModelNotFoundException, IntegrityException {
        if (medico == null){
            throw new EmptyModelException("El objeto médico está vacío");
        }
        if (medico.getId() == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        Medico medicoEntity = repository.find(medico.getId());
        if (medicoEntity == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        boolean emailExists = repository.findByEmail(medico.getCorreo(), medico.getId());
        if (emailExists){
            throw new IntegrityException("Ya existe un médico con el correo enviado");
        }
        // Actualizar los atributos del médico y de la dirección
        medicoEntity.setNombre(medico.getNombre());
        medicoEntity.setApellido(medico.getApellido());
        medicoEntity.setCorreo(medico.getCorreo());
        medicoEntity.setFechaNacimiento(medico.getFechaNacimiento());
        
        if(medico.getDireccion() != null) {
            medicoEntity.getDireccion().setDireccionDetallada(medico.getDireccion().getDireccionDetallada());
            medicoEntity.getDireccion().setBarrio(medico.getDireccion().getBarrio());
            medicoEntity.getDireccion().setCodigoPostal(medico.getDireccion().getCodigoPostal());
        }
        repository.edit(medicoEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Medico medico = repository.find(id);
        if (medico == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        repository.remove(medico);
    }
    
}
