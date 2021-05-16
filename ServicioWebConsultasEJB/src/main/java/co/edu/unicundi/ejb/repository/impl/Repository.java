package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.repository.IRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 * @param <T>
 */
public abstract class Repository<T> implements IRepository<T> {
    
    private final Class<T> entityClass;
    
    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();

    @Override
    public List<T> findAll(Integer pageNumber, Integer pageSize) {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        
        // Default values
        pageNumber = pageNumber == null || pageNumber <= 0 ? 1 : pageNumber;
        pageSize = pageSize == null  || pageSize <= 0 ? 10 : pageSize;

        String namedQuery = entityClass.getSimpleName() + ".findAll";
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, entityClass)
                                                .setFirstResult((pageNumber - 1) * pageSize) // Skip
                                                .setMaxResults(pageSize); // Take
        return (List<T>) query.getResultList();
    }

    @Override
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void edit(T entity) {
         getEntityManager().merge(entity);
    }

    @Override
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    @Override
    public int count(){
        String namedQuery = entityClass.getSimpleName() + ".count";
        Query query = getEntityManager().createNamedQuery(namedQuery);
        return ((Number)query.getSingleResult()).intValue();
    }
}
