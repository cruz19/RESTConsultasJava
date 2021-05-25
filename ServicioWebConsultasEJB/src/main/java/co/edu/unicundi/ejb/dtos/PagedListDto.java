package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 * @param <T>
 */
public class PagedListDto<T> implements Serializable {
    private Integer paginaActual;
    private Integer totalPaginas;
    private Integer tamanoPagina;
    private Integer totalRegistros;
    private List<T> items;
    
    public PagedListDto(List<T> items, Integer cantidad, Integer pagina, Integer tamano){
        this.items = items;
        this.totalRegistros = cantidad;
        this.tamanoPagina = (tamano==null || tamano<=0) ? 10 : tamano;
        this.paginaActual = (pagina==null || pagina<=0) ? 1 : pagina;
        this.totalPaginas = (int)Math.ceil(cantidad / (double) this.tamanoPagina);
    }

    public Integer getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Integer getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(Integer tamano) {
        this.tamanoPagina = tamano;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer cantidad) {
        this.totalRegistros = cantidad;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
