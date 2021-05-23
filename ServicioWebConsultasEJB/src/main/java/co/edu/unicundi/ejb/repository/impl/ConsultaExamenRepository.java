package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.repository.IConsultaExamenRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Stateless
public class ConsultaExamenRepository extends Repository<ConsultaExamen> implements IConsultaExamenRepository {

    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ConsultaExamenRepository(){
        super(ConsultaExamen.class);
    }

    @Override
    public List<ConsultaExamen> findByConsulta(Integer id) {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.findByConsulta", ConsultaExamen.class);
        query.setParameter("idConsulta", id);
        return query.getResultList();
    }

    @Override
    public List<ConsultaExamen> findByExamen(Integer id) {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.findByExamen", ConsultaExamen.class);
        query.setParameter("idExamen", id);
        return query.getResultList();
    }
}
