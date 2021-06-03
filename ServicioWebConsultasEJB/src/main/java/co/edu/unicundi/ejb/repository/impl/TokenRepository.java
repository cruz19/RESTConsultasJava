package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.entity.Token;
import co.edu.unicundi.ejb.repository.ITokenRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Stateless
public class TokenRepository extends Repository<Token> implements ITokenRepository {
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TokenRepository(){
        super(Token.class);
    }

    @Override
    public boolean findByToken(String token) {
        Query query = em.createNamedQuery("Token.buscarPorToken");
        query.setParameter("token", token);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }
    
    @Override
    public boolean findByTokenAndUser(String token, int usuarioId) {
        Query query = em.createNamedQuery("Token.buscar");
        query.setParameter("token", token);
        query.setParameter("usuario_id", usuarioId);
        int count = ((Number)query.getSingleResult()).intValue();
        return count > 0;
    }

    @Override
    public void removeToken(String token, int usuario_id) {
        Query query = getEntityManager().createNamedQuery("Token.eliminarToken");
        query.setParameter("token", token);
        query.setParameter("usuario_id", usuario_id);
        query.executeUpdate();
    }
}
