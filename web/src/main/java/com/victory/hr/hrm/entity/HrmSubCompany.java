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
public class HrmSubCompany extends BaseEntity<Long> {


    @Column(name = "name",nullable = false,length = 200)
    private String name;

    @Column(name = "description",length = 200)
    private String description;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "parentSub")
    private HrmSubCompany parent;

    @Column(name = "cancel")
    private Boolean cancel;

    public HrmSubCompany() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(HrmSubCompany parent) {
        this.parent = parent;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HrmSubCompany getParent() {
        return parent;
    }

    public boolean getCancel() {
        return cancel;
    }
}



