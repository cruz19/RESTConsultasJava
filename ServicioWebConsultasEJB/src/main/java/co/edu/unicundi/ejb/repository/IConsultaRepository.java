package co.edu.unicundi.ejb.repository;

import co.edu.unicundi.ejb.entity.Consulta;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IConsultaRepository {
    public List<Consulta> buscar();
    public Consulta buscarPorId(Integer id);
    public void guardar(Consulta consulta);
    public void actualizar(Consulta consulta);
    public void eliminar(Consulta consulta);
}
