package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.repository.IConsultaRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class ConsultaRepository extends Repository<Consulta> implements IConsultaRepository {

    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ConsultaRepository(){
        super(Consulta.class);
    }
}
