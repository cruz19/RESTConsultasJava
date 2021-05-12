package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 * @param <T>
 */
public class PagedListDto<T> implements Serializable {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalCount;
    private List<T> items;
    
    public PagedListDto(List<T> items, int count, int pageNumber, int pageSize){
        this.items = items;
        this.totalCount = count;
        this.pageSize = pageSize;
        this.currentPage = pageNumber;
        this.totalPages = (int)Math.ceil(count / (double) pageSize);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
