package co.edu.unicundi.ejb.service;

import co.edu.unicundi.ejb.dtos.PagedListDto;
import co.edu.unicundi.ejb.entity.MedicoView;
import co.edu.unicundi.ejb.interfaces.IMedicoViewService;
import co.edu.unicundi.ejb.repository.IMedicoViewRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
@Stateless
public class MedicoViewService implements IMedicoViewService {
    @EJB
    private IMedicoViewRepository repository;

    @Override
    public PagedListDto buscar(int pageNumber, int pageSize) {
        // Listar
        List<MedicoView> medicoViewList = repository.findAll(pageNumber, pageSize);
        // PagedList
        return new PagedListDto(medicoViewList, repository.count(), pageNumber, pageSize);
    }
}
