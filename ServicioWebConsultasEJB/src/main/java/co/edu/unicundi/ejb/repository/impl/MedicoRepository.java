package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.repository.IMedicoRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class MedicoRepository extends Repository<Medico> implements IMedicoRepository {
    
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public MedicoRepository(){
        super(Medico.class);
    }

    @Override
    public boolean findByEmail(String email, int id) {
        Query query = em.createNamedQuery("Medico.buscarPorEmail");
        query.setParameter("email", email);
        query.setParameter("id", id);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }
}
