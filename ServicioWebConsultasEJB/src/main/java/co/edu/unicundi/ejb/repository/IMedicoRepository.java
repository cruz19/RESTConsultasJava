package co.edu.unicundi.ejb.repository;

import co.edu.unicundi.ejb.entity.Medico;
import javax.ejb.Local;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Local
public interface IMedicoRepository extends IRepository<Medico> {
    public boolean findByEmail(String email);
}
