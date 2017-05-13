package com.victory.hr.common.search;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:9:02
 */
public class Searchable {
    //加上一个Request用于临时装参数

    private HttpServletRequest request;

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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
