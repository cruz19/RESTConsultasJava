package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 * @param <T>
 */
public class PagedListDto<T> implements Serializable {
    private Integer currentPage;
    private Integer totalPages;
    private Integer pageSize;
    private Integer totalCount;
    private List<T> items;
    
    public PagedListDto(List<T> items, Integer count, Integer pageNumber, Integer pageSize){
        this.items = items;
        this.totalCount = count;
        this.pageSize = pageSize;
        this.currentPage = pageNumber;
        this.totalPages = (int)Math.ceil(count / (double) pageSize);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
