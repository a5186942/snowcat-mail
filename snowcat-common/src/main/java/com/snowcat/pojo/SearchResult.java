package com.snowcat.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    private List<SearchItemResult> searchItemResults;
    private Integer totalPages;
    private Integer totalCounts;

    @Override
    public String toString() {
        return "SearchResult{" +
                "searchItemResults=" + searchItemResults +
                ", totalPages=" + totalPages +
                ", totalCounts=" + totalCounts +
                '}';
    }

    public List<SearchItemResult> getSearchItemResults() {
        return searchItemResults;
    }

    public void setSearchItemResults(List<SearchItemResult> searchItemResults) {
        this.searchItemResults = searchItemResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }
}
