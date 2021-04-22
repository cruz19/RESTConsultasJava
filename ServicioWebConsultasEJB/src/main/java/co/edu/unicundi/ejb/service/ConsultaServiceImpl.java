package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IConsultaService;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

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
    public List<Consulta> buscar() {
        return consultaRepository.findAll();
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
    public void guardar(Consulta consulta) throws EmptyModelException, IntegrityException {
        if (consulta == null){
            throw new EmptyModelException("El objeto consulta está vacío");
        }
        if(consulta.getDetallesConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetallesConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        if (consulta.getMedico() != null){
            boolean emailExists = medicoRepository.findByEmail(consulta.getMedico().getCorreo(), -1);
            if (emailExists){
                throw new IntegrityException("Ya existe un médico con el correo enviado");
            }
            if (consulta.getMedico().getDireccion() != null){
                consulta.getMedico().getDireccion().setMedico(consulta.getMedico());
            }   
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
        if (consulta.getMedico() != null){
            boolean emailExists = medicoRepository.findByEmail(consulta.getMedico().getCorreo(), -1);
            if (emailExists){
                throw new IntegrityException("Ya existe un médico con el correo enviado");
            }
            consultaEntity.setMedico(consulta.getMedico());
            // Dirección
            if (consulta.getMedico().getDireccion() != null){
                consultaEntity.getMedico().setDireccion(consulta.getMedico().getDireccion());
                consultaEntity.getMedico().getDireccion().setMedico(consultaEntity.getMedico());
            }
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
