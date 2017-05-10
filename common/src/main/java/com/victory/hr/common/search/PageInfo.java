package com.victory.hr.common.search;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/2/9.
 * Time:9:42
 */
public class PageInfo {
    private Long totals;
    private List data;

    public PageInfo() {
    }

    public PageInfo(Long totals, List data) {
        this.totals = totals;
        this.data = data;
    }

    public Long getTotals() {
        return totals;
    }

    public void setTotals(Long totals) {
        this.totals = totals;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
