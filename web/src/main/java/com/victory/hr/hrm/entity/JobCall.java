package com.victory.hr.hrm.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 社会职称表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:22
 */
@Entity
public class JobCall extends BaseEntity<Long> {

    @Column(name = "name",nullable = false)
    private String name;

    //评定日期
    @Column(name = "date")
    private String date;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id", nullable = false)
    private HrmResource resource;

    public JobCall() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String description) {
        this.date = description;
    }
}
