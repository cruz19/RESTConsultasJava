package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.MedicoDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.Medico;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Local
public interface IMedicoService {
    public PagedListDto buscar(Integer pageNumber, Integer pageSize, boolean details);
    public MedicoDto buscarPorId(Integer id, boolean details) throws ModelNotFoundException;
    public void guardar(Medico medico) throws EmptyModelException, IntegrityException;
    public void actualizar(Medico medico) throws EmptyModelException, ModelNotFoundException, IntegrityException;
    public void eliminar(Integer id) throws ModelNotFoundException;
}
