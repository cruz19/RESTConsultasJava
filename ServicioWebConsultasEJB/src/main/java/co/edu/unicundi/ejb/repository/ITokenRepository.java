package co.edu.unicundi.ejb.repository;

import co.edu.unicundi.ejb.entity.Token;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface ITokenRepository extends IRepository<Token> {
    public boolean findByToken(String token);
    public boolean findByTokenAndUser(String token, int usuarioId);
    public void removeToken(String token, int usuarioId);
}

