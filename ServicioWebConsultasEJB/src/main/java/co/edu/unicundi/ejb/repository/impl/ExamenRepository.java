package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.repository.IExamenRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class ExamenRepository extends Repository<Examen> implements IExamenRepository {
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ExamenRepository(){
        super(Examen.class);
    }
}
