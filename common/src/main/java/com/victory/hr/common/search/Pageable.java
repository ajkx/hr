package com.victory.hr.common.search;

import org.hibernate.criterion.Order;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:9:11
 */
public class Pageable {

    private int cPage;

    private int pSize;

    private Order order;

    public Pageable() {
        this.order = Order.asc("id");
    }

    public int getcPage() {
        return cPage;
    }

    public void setcPage(int cPage) {
        this.cPage = cPage;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
