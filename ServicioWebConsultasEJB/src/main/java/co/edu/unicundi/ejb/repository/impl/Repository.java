package co.edu.unicundi.ejb.repository.impl;

import co.edu.unicundi.ejb.repository.IRepository;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 * @param <T>
 */
public class Repository<T> implements IRepository<T> {
    
    @PersistenceContext(unitName = "co.edu.unicundi_ConsultaPU")
    protected EntityManager em;
    
    @Override
    public List<T> buscar() {
        Type tType = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class<?> clazz = (Class<?>) tType;
        String namedQuery = clazz.getSimpleName() + ".listarTodos";
        TypedQuery<T> query = (TypedQuery<T>) em.createNamedQuery(namedQuery, clazz);
        return query.getResultList();
    }

    @Override
    public T buscarPorId(Integer id) {
        Type tType = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class<?> clazz = (Class<?>) tType;
        return (T) em.find(clazz, id);
    }

    @Override
    public void guardar(T entity) {
        em.persist(entity);
    }

    @Override
    public void actualizar(T entity) {
        em.merge(entity);
    }

    @Override
    public void eliminar(T entity) {
        em.remove(entity);
    }
}
