package co.edu.unicundi.ejb.repository;

import co.edu.unicundi.ejb.entity.ConsultaExamen;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IConsultaExamenRepository extends IRepository<ConsultaExamen> {
    public List<ConsultaExamen> findByConsulta(Integer idConsulta);
    public boolean findByPK(Integer idConsulta, Integer idExamen);
    public void removeByPK(Integer idConsulta, Integer idExamen);
}





