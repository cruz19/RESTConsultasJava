package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.ConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IConsultaService {
    public PagedListDto buscar(Integer pageNumber, Integer pageSize, boolean details);
    public ConsultaDto buscarPorId(Integer id, boolean details) throws ModelNotFoundException;
    public void guardar(Consulta consulta) throws EmptyModelException, ModelNotFoundException, IntegrityException;
    public void actualizar(Consulta consulta) throws EmptyModelException, ModelNotFoundException, IntegrityException;
    public void eliminar(Integer id) throws ModelNotFoundException;
}
