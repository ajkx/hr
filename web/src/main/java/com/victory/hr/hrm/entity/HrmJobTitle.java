package com.victory.hr.hrm.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:17:26
 */
@Entity
@Table(name = "HrmJobTitles")
public class HrmJobTitle extends BaseEntity<Integer>{

    @Column(name = "jobtitlename")
    private String name;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "jobdepartmentid")
    private HrmDepartment department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HrmDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HrmDepartment department) {
        this.department = department;
    }
}
