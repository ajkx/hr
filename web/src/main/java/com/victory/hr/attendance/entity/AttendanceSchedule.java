package com.victory.hr.attendance.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.victory.hr.attendance.enums.ScheduleType;
import com.victory.hr.common.controller.converter.JsonTimeDeserializer;
import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

/**
 * 班次表
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:35
 */
@Entity
@Table(name = "EHR_AttendanceSchedule")
public class AttendanceSchedule extends BaseEntity<Integer>{

    @Column
    private String name;

    @Column
    private String simpleName;

    @Column
    private ScheduleType scheduleType;

    //第一次上班打卡时间
    @Column
    private Time first_time_up;

    //第一次下班打卡时间
    @Column
    private Time first_time_down;

    //第二次上班打卡时间
    @Column
    private Time second_time_up;

    //第二次下班打卡时间
    @Column
    private Time second_time_down;

    //第三次上班打卡时间
    @Column
    private Time third_time_up;

    //第三次下班打卡时间
    @Column
    private Time third_time_down;

    //上班有效打卡范围
    @Column
    private Long scope_up;

    //下班有效打卡范围
    @Column
    private Long scope_down;

    //出勤合计时间
    @Column
    private Long attendanceTime;

    //下班是否需要打卡
    @Column(name = "isPunch")
    private Boolean punch;

    //迟到早退的限定时间
    @Column
    private Long offsetTime;

    @Column
    private Boolean acrossDay;

    @Column
    private String description;

    //单班次的休息时间
    @Column
    private Boolean haveRest;

    @Column
    private Time beginRest;

    @Column
    private Time endRest;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "monday")
    private Set<AttendanceGroup> mondays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "tuesday")
    private Set<AttendanceGroup> tuesdays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "wednesday")
    private Set<AttendanceGroup> wednesdays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "thursday")
    private Set<AttendanceGroup> thursdays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "friday")
    private Set<AttendanceGroup> fridays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "saturday")
    private Set<AttendanceGroup> saturdays;

    @OneToMany(targetEntity = AttendanceGroup.class,mappedBy = "sunday")
    private Set<AttendanceGroup> sundays;

    //是否休息班次
    @Column(name = "isRest")
    private Boolean rest;

    //关联的考勤组
    @ManyToMany(targetEntity = AttendanceSchedule.class)
    @JoinTable(name = "EHR_group_schedule",
            joinColumns = @JoinColumn(name="schedule_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"))
    private Set<AttendanceGroup> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimplename() {
        return simpleName;
    }

    public void setSimplename(String simplename) {
        this.simpleName = simplename;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Time getFirst_time_up() {
        return first_time_up;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setFirst_time_up(Time first_time_up) {
        this.first_time_up = first_time_up;
    }

    public Time getFirst_time_down() {
        return first_time_down;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setFirst_time_down(Time first_time_down) {
        this.first_time_down = first_time_down;
    }

    public Time getSecond_time_up() {
        return second_time_up;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setSecond_time_up(Time second_time_up) {
        this.second_time_up = second_time_up;
    }

    public Time getSecond_time_down() {
        return second_time_down;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setSecond_time_down(Time second_time_down) {
        this.second_time_down = second_time_down;
    }

    public Time getThird_time_up() {
        return third_time_up;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setThird_time_up(Time third_time_up) {
        this.third_time_up = third_time_up;
    }

    public Time getThird_time_down() {
        return third_time_down;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setThird_time_down(Time third_time_down) {
        this.third_time_down = third_time_down;
    }

    public Long getScope_up() {
        return scope_up;
    }

    public void setScope_up(Long scope_up) {
        this.scope_up = scope_up;
    }

    public Long getScope_down() {
        return scope_down;
    }

    public void setScope_down(Long scope_down) {
        this.scope_down = scope_down;
    }

    public Long getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Long attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public Boolean getPunch() {
        return punch;
    }

    public void setPunch(Boolean punch) {
        this.punch = punch;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public Long getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(Long offsetTime) {
        this.offsetTime = offsetTime;
    }

    public Boolean getAcrossDay() {
        return acrossDay;
    }

    public void setAcrossDay(Boolean acrossDay) {
        this.acrossDay = acrossDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AttendanceGroup> getMondays() {
        return mondays;
    }

    public void setMondays(Set<AttendanceGroup> mondays) {
        this.mondays = mondays;
    }

    public Set<AttendanceGroup> getTuesdays() {
        return tuesdays;
    }

    public void setTuesdays(Set<AttendanceGroup> tuesdays) {
        this.tuesdays = tuesdays;
    }

    public Set<AttendanceGroup> getWednesdays() {
        return wednesdays;
    }

    public void setWednesdays(Set<AttendanceGroup> wednesdays) {
        this.wednesdays = wednesdays;
    }

    public Set<AttendanceGroup> getThursdays() {
        return thursdays;
    }

    public void setThursdays(Set<AttendanceGroup> thursdays) {
        this.thursdays = thursdays;
    }

    public Set<AttendanceGroup> getFridays() {
        return fridays;
    }

    public void setFridays(Set<AttendanceGroup> fridays) {
        this.fridays = fridays;
    }

    public Set<AttendanceGroup> getSaturdays() {
        return saturdays;
    }

    public void setSaturdays(Set<AttendanceGroup> saturdays) {
        this.saturdays = saturdays;
    }

    public Set<AttendanceGroup> getSundays() {
        return sundays;
    }

    public void setSundays(Set<AttendanceGroup> sundays) {
        this.sundays = sundays;
    }

    public Boolean getRest() {
        return rest;
    }

    public void setRest(Boolean rest) {
        this.rest = rest;
    }

    public Set<AttendanceGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AttendanceGroup> groups) {
        this.groups = groups;
    }

    public Boolean getHaveRest() {
        return haveRest;
    }

    public void setHaveRest(Boolean haveRest) {
        this.haveRest = haveRest;
    }

    public Time getBeginRest() {
        return beginRest;
    }
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setBeginRest(Time beginRest) {
        this.beginRest = beginRest;
    }

    public Time getEndRest() {
        return endRest;
    }
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setEndRest(Time endRest) {
        this.endRest = endRest;
    }

    @Override
    public String toString() {
        return "AttendanceSchedule{" +
                "name='" + name + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", scheduleType=" + scheduleType +
                ", first_time_up=" + first_time_up +
                ", first_time_down=" + first_time_down +
                ", second_time_up=" + second_time_up +
                ", second_time_down=" + second_time_down +
                ", third_time_up=" + third_time_up +
                ", third_time_down=" + third_time_down +
                ", scope_up=" + scope_up +
                ", scope_down=" + scope_down +
                ", attendanceTime=" + attendanceTime +
                ", punch=" + punch +
                ", offsetTime=" + offsetTime +
                ", acrossDay=" + acrossDay +
                ", description='" + description + '\'' +
                ", haveRest=" + haveRest +
                ", beginRest=" + beginRest +
                ", endRest=" + endRest +
                ", rest=" + rest +
                '}';
    }
}
