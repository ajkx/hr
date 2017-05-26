package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.SpecialType;
import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ajkx
 * Date: 2017/5/22.
 * Time:10:10
 */
@Entity
@Table(name = "EHR_SpecialDate")
public class SpecialDate extends BaseEntity<Integer>{


    private Date date;

    @Column(name = "group_id")
    private AttendanceGroup group;

    @ManyToOne(targetEntity = AttendanceSchedule.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private AttendanceSchedule schedule;

    private SpecialType specialType;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AttendanceGroup getGroup() {
        return group;
    }

    public void setGroup(AttendanceGroup group) {
        this.group = group;
    }

    public AttendanceSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(AttendanceSchedule schedule) {
        this.schedule = schedule;
    }

    public SpecialType getSpecialType() {
        return specialType;
    }

    public void setSpecialType(SpecialType specialType) {
        this.specialType = specialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SpecialDate that = (SpecialDate) o;

        if (this.getId() == that.getId()) {
            return true;
        }else{
            return this.group.getId() == that.getGroup().getId() && this.date.equals(that.getDate()) && this.specialType == that.getSpecialType();
        }

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        result = 31 * result + (specialType != null ? specialType.hashCode() : 0);
        return result;
    }
}
