package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by ajkx
 * Date: 2017/5/24.
 * Time:9:00
 *
 * 异常记录表
 */
@Entity
@Table(name = "EHR_RepairRecord")
public class RepairRecord extends BaseEntity<Integer>{

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    private Date date;

    //修改那个上下班 1 - firstUp 2 - firstDown 3 - secondUp 4 - secondDown 5 - thirdUp 6 - thirdDown 7 - otFirstUp 8 - otFirstDown
    //多个按,号隔开
    private String position;

    private String reason;

    private Status status;

    @ManyToOne(targetEntity = AttendanceDetail.class)
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private AttendanceDetail detail;

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public AttendanceDetail getDetail() {
        return detail;
    }

    public void setDetail(AttendanceDetail detail) {
        this.detail = detail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
