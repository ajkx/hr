package com.victory.hr.hrm.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 工作经历表
 * Created by ajkx on 2017/2/11.
 */
@Entity
public class WorkInfo extends BaseEntity<Long> {

    //公司名称
    @Column(name = "company",nullable = false)
    private String company;

    @Column(name = "jobPosition")
    private String jobPosition;

    @Column(name = "beginDate")
    private String beginDate;

    @Column(name = "endDate")
    private String endDate;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    public WorkInfo() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public WorkInfo(String company, String jobPosition, String beginDate, String endDate, HrmResource resource) {

        this.company = company;
        this.jobPosition = jobPosition;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.resource = resource;
    }
}
