package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Rol;
import co.edu.unicundi.ejb.repository.IRolRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Stateless
public class RolRepository extends Repository<Rol> implements IRolRepository {
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public RolRepository(){
        super(Rol.class);
    }
}
