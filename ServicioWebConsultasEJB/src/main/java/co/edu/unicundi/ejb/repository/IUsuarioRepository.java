package co.edu.unicundi.ejb.repository;

import co.edu.unicundi.ejb.entity.Usuario;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IUsuarioRepository extends IRepository<Usuario> {
    public boolean findByEmail(String email);
    public boolean findByUsername(String username);
    public Usuario login(String username, String contrasena);
}
