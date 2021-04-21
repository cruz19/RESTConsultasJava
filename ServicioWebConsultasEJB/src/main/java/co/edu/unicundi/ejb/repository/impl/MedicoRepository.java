package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class MedicoRepository extends Repository<Medico> implements IMedicoRepository {

    @Override
    public Medico buscarPorCorreo(String correo) {
        try{
            Query query = em.createNamedQuery("Medico.buscarPorCorreo", Medico.class);
            query.setParameter("correo", correo);
            return (Medico) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
