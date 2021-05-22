package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.DetalleConsultaDto;
import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Local
public interface IDetalleConsultaService {
    public PagedListDto buscar(Integer pageNumber, Integer pageSize, boolean details);
    public DetalleConsultaDto buscarPorId(Integer id, boolean details) throws ModelNotFoundException;
    public void guardar(DetalleConsulta detalleConsulta) throws EmptyModelException, ModelNotFoundException, IntegrityException;
    public void actualizar(DetalleConsulta detalleConsulta) throws EmptyModelException, ModelNotFoundException;
    public void eliminar(Integer id) throws ModelNotFoundException;
}
