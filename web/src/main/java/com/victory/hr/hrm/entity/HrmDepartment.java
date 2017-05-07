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
public class HrmDepartment extends BaseEntity<Long>{


    @Column(name = "departmentName",nullable = false,length = 200)
    private String name;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "subCompanyId",nullable = false)
    private HrmSubCompany subCompany;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "parentDep")
    private HrmDepartment parent;

    @Column(name = "cancel")
    private boolean cancel;

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

    public boolean getCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
