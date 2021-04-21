package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import co.edu.unicundi.ejb.interfaces.IMedicoService;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class MedicoServiceImpl implements IMedicoService {
    
    @EJB
    private IMedicoRepository repository;

    @Override
    public List<Medico> buscar() {
        return repository.findAll();
    }

    @Override
    public Medico buscarPorId(Integer id) throws ModelNotFoundException {
        Medico medico = repository.find(id);
        if (medico == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        return medico;
    }

    @Override
    public void guardar(Medico medico) throws EmptyModelException, IntegrityException {
        if (medico == null){
            throw new EmptyModelException("El objeto médico está vacío");
        }
        if (repository.findByEmail(medico.getCorreo())){
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
        Medico medicoEntity = this.buscarPorId(medico.getId());
        if (medicoEntity == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        if (repository.findByEmail(medico.getCorreo())){
            throw new IntegrityException("Ya existe un médico con el correo enviado");
        }
        // Actualizar los atributos del médico y de la dirección
        medicoEntity.setNombre(medico.getNombre());
        medicoEntity.setApellido(medico.getApellido());
        medicoEntity.setCorreo(medico.getCorreo());
        
        if(medico.getDireccion() != null) {
            medicoEntity.getDireccion().setDireccionDetallada(medico.getDireccion().getDireccionDetallada());
            medicoEntity.getDireccion().setBarrio(medico.getDireccion().getBarrio());
            medicoEntity.getDireccion().setCodigoPostal(medico.getDireccion().getCodigoPostal());
        }
        repository.edit(medicoEntity);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Medico medico = this.buscarPorId(id);
        if (medico == null){
            throw new ModelNotFoundException("No existe un médico con el id enviado");
        }
        repository.remove(medico);
    }
    
}
