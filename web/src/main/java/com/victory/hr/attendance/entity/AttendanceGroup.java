package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.GroupType;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by ajkx on 2017/2/16.
 */
@Entity
@Table(name = "EHR_AttendanceGroup")
public class AttendanceGroup extends BaseEntity<Integer>{

    @Column
    private String name;

    @Column
    private GroupType groupType;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "monday")
    private AttendanceSchedule monday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "tuesday")
    private AttendanceSchedule tuesday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "wednesday")
    private AttendanceSchedule wednesday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "thursday")
    private AttendanceSchedule thursday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "friday")
    private AttendanceSchedule friday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "saturday")
    private AttendanceSchedule saturday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "sunday")
    private AttendanceSchedule sunday;

    @Column(name = "holidayRest")
    private Boolean holidayRest;

    private String description;

    //相关人员
    @OneToMany(targetEntity = HrmResource.class)
    @JoinTable(name = "EHR_group_resource",
            joinColumns =@JoinColumn(name = "group_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id",referencedColumnName = "id",unique = true))
    private Set<HrmResource> resources;

    //自由排班相关的班次
    @ManyToMany(targetEntity = AttendanceSchedule.class)
    @JoinTable(name = "EHR_group_schedule",
        joinColumns = @JoinColumn(name="group_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "schedule_id",referencedColumnName = "id"))
    private Set<AttendanceSchedule> schedules;

    //必须打卡的日期
    @ElementCollection(targetClass = Date.class)
    @CollectionTable(name="EHR_MustPunchDate",joinColumns = @JoinColumn(name = "group_id",nullable = false))
    @Column(name = "punchDate")
    private Set<Date> mustPunchDate;

    //不需要打卡的日期
    @ElementCollection(targetClass = Date.class)
    @CollectionTable(name="EHR_NoNeedPunchDate",joinColumns = @JoinColumn(name = "group_id",nullable = false))
    @Column(name = "noNeedDate")
    private Set<Date> noNeedPunchDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public AttendanceSchedule getMonday() {
        return monday;
    }

    public void setMonday(AttendanceSchedule monday) {
        this.monday = monday;
    }

    public AttendanceSchedule getTuesday() {
        return tuesday;
    }

    public void setTuesday(AttendanceSchedule tuesday) {
        this.tuesday = tuesday;
    }

    public AttendanceSchedule getWednesday() {
        return wednesday;
    }

    public void setWednesday(AttendanceSchedule wednesday) {
        this.wednesday = wednesday;
    }

    public AttendanceSchedule getThursday() {
        return thursday;
    }

    public void setThursday(AttendanceSchedule thursday) {
        this.thursday = thursday;
    }

    public AttendanceSchedule getFriday() {
        return friday;
    }

    public void setFriday(AttendanceSchedule friday) {
        this.friday = friday;
    }

    public AttendanceSchedule getSaturday() {
        return saturday;
    }

    public void setSaturday(AttendanceSchedule saturday) {
        this.saturday = saturday;
    }

    public AttendanceSchedule getSunday() {
        return sunday;
    }

    public void setSunday(AttendanceSchedule sunday) {
        this.sunday = sunday;
    }

    public Boolean getHolidayRest() {
        return holidayRest;
    }

    public void setHolidayRest(Boolean holidayRest) {
        this.holidayRest = holidayRest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HrmResource> getResources() {
        return resources;
    }

    public void setResources(Set<HrmResource> resources) {
        this.resources = resources;
    }

    public Set<AttendanceSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<AttendanceSchedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Date> getMustPunchDate() {
        return mustPunchDate;
    }

    public void setMustPunchDate(Set<Date> mustPunchDate) {
        this.mustPunchDate = mustPunchDate;
    }

    public Set<Date> getNoNeedPunchDate() {
        return noNeedPunchDate;
    }

    public void setNoNeedPunchDate(Set<Date> noNeedPunchDate) {
        this.noNeedPunchDate = noNeedPunchDate;
    }

}
