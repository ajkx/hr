package com.victory.hr.common.search;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:9:02
 */
public class Searchable {

    private List<SearchFilter> searchFilters;

    private Pageable pageable;

    public List<SearchFilter> getSearchFilters() {
        return searchFilters;
    }

    public void setSearchFilters(List<SearchFilter> searchFilters) {
        this.searchFilters = searchFilters;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
