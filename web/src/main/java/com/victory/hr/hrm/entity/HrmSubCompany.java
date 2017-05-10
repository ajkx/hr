package com.victory.hr.hrm.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 分部表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 9:49
 */
@Entity
public class HrmSubCompany extends BaseEntity<Integer> {


    @Column(name = "subcompanyname")
    private String name;

    @ManyToOne(targetEntity = HrmSubCompany.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "supsubcomid")
    private HrmSubCompany parent;

    @Column(name = "canceled")
    private Boolean cancel;

    public HrmSubCompany() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HrmSubCompany getParent() {
        return parent;
    }

    public void setParent(HrmSubCompany parent) {
        this.parent = parent;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}



