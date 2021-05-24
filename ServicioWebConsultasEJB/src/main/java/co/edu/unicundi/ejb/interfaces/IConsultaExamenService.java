package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.ExamenesConsultaDto;
import co.edu.unicundi.ejb.entity.ConsultaExamen;
import co.edu.unicundi.ejb.exceptions.EmptyModelException;
import co.edu.unicundi.ejb.exceptions.IntegrityException;
import co.edu.unicundi.ejb.exceptions.ModelNotFoundException;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Stiven Cruz
 */
@Local
public interface IConsultaExamenService {
    public ExamenesConsultaDto buscarPorConsulta(Integer idConsulta) throws ModelNotFoundException;
    public void guardar(ConsultaExamen consultaExamen) throws EmptyModelException, ModelNotFoundException, IntegrityException;
    public void eliminar(Integer idConsulta, Integer idExamen) throws ModelNotFoundException;
}


