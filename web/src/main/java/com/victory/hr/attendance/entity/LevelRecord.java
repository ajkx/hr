package com.victory.hr.attendance.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.victory.hr.attendance.enums.LevelType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.controller.converter.JsonDateDeserializer;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 请假记录表
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:29
 */
@Entity
@Table(name = "EHR_LevelRecord")
public class LevelRecord extends BaseEntity<Integer>{


    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    @Column
    private LevelType type;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "beginDate")
    private Date date;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "endDate")
    private Date endDate;

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    //请假时数
    @Column
    private Long totalTime;

    //请假原因
    @Column
    private String reason;

    //状态
    @Column
    private Status status;

    //备注
    @Column
    private String remark;

    @ManyToMany(targetEntity = AttendanceDetail.class)
    @JoinTable(name = "EHR_detail_levelRecord",
            joinColumns = @JoinColumn(name = "record_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id",referencedColumnName = "id"))
    private Set<AttendanceDetail> details;

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public LevelType getType() {
        return type;
    }

    public void setType(LevelType type) {
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

    public Set<AttendanceDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<AttendanceDetail> details) {
        this.details = details;
    }
}
