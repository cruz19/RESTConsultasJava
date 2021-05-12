package co.edu.unicundi.ejb.interfaces;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import javax.ejb.Local;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Local
public interface IMedicoViewService {
    public PagedListDto buscar(int pageNumber, int pageSize);
}

