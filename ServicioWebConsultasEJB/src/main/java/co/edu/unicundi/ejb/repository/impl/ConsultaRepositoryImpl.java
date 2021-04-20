package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class ConsultaRepositoryImpl implements IConsultaRepository {
    
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    private EntityManager em;

    @Override
    public List<Consulta> buscar() {
       TypedQuery<Consulta> query = em.createNamedQuery("Consulta.listarTodos", Consulta.class);
       return query.getResultList();
    }

    @Override
    public Consulta buscarPorId(Integer id) {
        return em.find(Consulta.class, id);
    }

    @Override
    public void guardar(Consulta consulta) {
        em.persist(consulta);   
    }

    @Override
    public void actualizar(Consulta consulta) {
        em.merge(consulta);
    }

    @Override
    public void eliminar(Consulta consulta) {
        em.remove(consulta);
    }
    
}
