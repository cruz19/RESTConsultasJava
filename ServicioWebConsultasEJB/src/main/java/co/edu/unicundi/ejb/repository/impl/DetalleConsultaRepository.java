package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.repository.IDetalleConsultaRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class DetalleConsultaRepository extends Repository<DetalleConsulta> implements IDetalleConsultaRepository {

    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DetalleConsultaRepository(){
        super(DetalleConsulta.class);
    }
    
}
