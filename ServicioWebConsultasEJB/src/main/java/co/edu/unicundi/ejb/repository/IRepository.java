package co.edu.unicundi.ejb.repository;

import java.util.List;
import javax.ejb.Local;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 * @param <T> entity
 */
@Local
public interface IRepository<T> {
    public List<T> buscar();
    public T buscarPorId(Integer id);
    public void guardar(T entity);
    public void actualizar(T entity);
    public void eliminar(T entity);
}
