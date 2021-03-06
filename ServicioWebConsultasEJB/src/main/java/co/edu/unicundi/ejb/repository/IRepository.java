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
    public List<T> findAll(Integer pageNumber, Integer pageSize);
    public T find(Object id);
    public void create(T entity);
    public void edit(T entity);
    public void remove(Object id);
    public int count();
}
