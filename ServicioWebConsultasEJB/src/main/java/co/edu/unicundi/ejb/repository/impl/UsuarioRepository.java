package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Usuario;
import co.edu.unicundi.ejb.repository.IUsuarioRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class UsuarioRepository extends Repository<Usuario> implements IUsuarioRepository {
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UsuarioRepository(){
        super(Usuario.class);
    }

    @Override
    public Usuario login(String username, String contrasena) {
        try {
            Query query = em.createNamedQuery("Usuario.login");
            query.setParameter("username", username);
            query.setParameter("contrasena", contrasena);
            return (Usuario) query.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public boolean findByEmail(String email) {
        Query query = em.createNamedQuery("Usuario.buscarPorEmail");
        query.setParameter("email", email);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }

    @Override
    public boolean findByUsername(String username) {
        Query query = em.createNamedQuery("Usuario.buscarPorUsername");
        query.setParameter("username", username);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }
}
