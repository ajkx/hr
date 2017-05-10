package com.victory.hr.hrm.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 部门表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 10:20
 */
@Entity
public class HrmDepartment extends BaseEntity<Integer>{


    @Column(name = "departmentname",nullable = false,length = 200)
    private String name;

    @ManyToOne(targetEntity = HrmSubCompany.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "subcompanyid1",nullable = false)
    private HrmSubCompany subCompany;

    @ManyToOne(targetEntity = HrmDepartment.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "supdepid")
    private HrmDepartment parent;

    @Column(name = "canceled")
    private Boolean cancel;

    public HrmDepartment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HrmSubCompany getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(HrmSubCompany subCompany) {
        this.subCompany = subCompany;
    }

    public HrmDepartment getParent() {
        return parent;
    }

    public void setParent(HrmDepartment parent) {
        this.parent = parent;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
