package com.victory.hr.attendance.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.prism.impl.Disposer;
import com.victory.hr.attendance.enums.RecordType;
import com.victory.hr.common.controller.converter.JsonDateDeserializer;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.util.Date;


/**
 * 打卡原记录表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:35
 */
@Entity
@Table(name = "EHR_AttendanceRecord")
public class AttendanceRecord extends BaseEntity<Integer>{

//    @ManyToOne(targetEntity = Card.class)
//    @JoinColumn(name="card")
//    private Card card;LevelRecord

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    @Column(name = "machineNo")
    private String machineNo;

    @Column(name = "date")
    private Date date;

    @Column
    private RecordType type;

    @Column
    private String reason;

    public AttendanceRecord() {
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public Date getDate() {
        return date;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }
}
