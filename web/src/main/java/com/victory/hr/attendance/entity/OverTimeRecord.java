package com.victory.hr.attendance.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.victory.hr.attendance.enums.OverTimeType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.controller.converter.JsonDateDeserializer;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.sys.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/10.
 * Time:18:48
 */
@Entity
@Table(name = "EHR_OverTimeRecord")
public class OverTimeRecord extends BaseEntity<Integer>{


    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    //1代表平时加班 2代表周末加班 3代表节日加班
    @Column
    private OverTimeType type;

//    加班开始日期 时间
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "beginDate")
    private Date date;

    //加班结束日期 时间
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "endDate")
    private Date endDate;

    //实际上班打卡时间
    @Column(name = "actualBeginDate")
    private Date actualBeginDate;

    //实际下班打卡时间
    @Column(name = "actualEndDate")
    private Date actualEndDate;

//    计划加班时数
    @Column
    private Long totalTime;

//    实际加班时数
    @Column
    private Long actualTotalTime;

    //加班原因
    @Column
    private String reason;

    //状态
    @Column
    private Status status;

    //备注
    @Column
    private String remark;

    //是否连班
    @Column(name = "isLink")
    private Boolean link;

    @ManyToOne(targetEntity = AttendanceDetail.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private AttendanceDetail detail;

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public OverTimeType getType() {
        return type;
    }

    public void setType(OverTimeType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getActualBeginDate() {
        return actualBeginDate;
    }

    public void setActualBeginDate(Date actualBeginDate) {
        this.actualBeginDate = actualBeginDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getActualTotalTime() {
        return actualTotalTime;
    }

    public void setActualTotalTime(Long actualTotalTime) {
        this.actualTotalTime = actualTotalTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getLink() {
        return link;
    }

    public void setLink(Boolean link) {
        this.link = link;
    }

    public AttendanceDetail getDetail() {
        return detail;
    }

    public void setDetail(AttendanceDetail detail) {
        this.detail = detail;
    }
}
