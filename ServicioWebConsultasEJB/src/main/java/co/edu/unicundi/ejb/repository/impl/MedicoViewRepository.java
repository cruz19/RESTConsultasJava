package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.MedicoView;
import co.edu.unicundi.ejb.repository.IMedicoViewRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class MedicoViewRepository extends Repository<MedicoView> implements IMedicoViewRepository {
    
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public MedicoViewRepository(){
        super(MedicoView.class);
    }
}
