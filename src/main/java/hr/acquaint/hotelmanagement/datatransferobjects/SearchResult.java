package hr.acquaint.hotelmanagement.datatransferobjects;

import java.io.Serializable;
import java.util.List;

/**
 * Search result data transfer object
 * @param <T>
 */
public class SearchResult<T> implements Serializable {
    private long totalCount;
    private List<T> data;

    public SearchResult() {
    }

    public SearchResult(long totalCount, List<T> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
