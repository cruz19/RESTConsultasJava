package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.ExamenDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Examen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IExamenService {
    public PagedListDto buscar(Integer pageNumber, Integer pageSize);
    public ExamenDto buscarPorId(Integer id) throws ModelNotFoundException;
    public void guardar(Examen examen) throws EmptyModelException;
    public void actualizar(Examen examen) throws EmptyModelException, ModelNotFoundException;
    public void eliminar(Integer id) throws ModelNotFoundException;
}

