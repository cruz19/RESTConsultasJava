package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.entity.Consulta;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IConsultaService {
    public List<Consulta> buscar();
    public Consulta buscarPorId(Integer id) throws ModelNotFoundException;
    public void guardar(Consulta consulta) throws EmptyModelException;
    public void actualizar(Consulta consulta) throws EmptyModelException, ModelNotFoundException;
    public void eliminar(Integer id) throws ModelNotFoundException;
}
