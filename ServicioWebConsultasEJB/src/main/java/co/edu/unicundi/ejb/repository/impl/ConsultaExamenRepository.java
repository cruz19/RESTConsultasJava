package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.repository.IConsultaExamenRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<ConsultaExamen> findByConsulta(Integer idConsulta) {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.buscarPorConsulta", ConsultaExamen.class);
        query.setParameter("idConsulta", idConsulta);
        return query.getResultList();
    }
    
    @Override
    public List<ConsultaExamen> findByExamen(Integer idExamen) {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.buscarPorExamen", ConsultaExamen.class);
        query.setParameter("idExamen", idExamen);
        return query.getResultList();
    }

    @Override
    public boolean findByPK(Integer idConsulta, Integer idExamen) {
        Query query = em.createNamedQuery("ConsultaExamen.buscar");
        query.setParameter("idConsulta", idConsulta);
        query.setParameter("idExamen", idExamen);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }

    @Override
    public void removeByPK(Integer idConsulta, Integer idExamen) {
        Query query = em.createNamedQuery("ConsultaExamen.eliminar");
        query.setParameter("idConsulta", idConsulta);
        query.setParameter("idExamen", idExamen);
        query.executeUpdate();
    }
    
}
