package com.victory.hr.vo;

/**
 * Created by ajkx
 * Date: 2017/1/6.
 * Time:9:12
 */
public class ScheduleInfoVo {
    public Integer id;
    public String resouceId;
    public Integer scheduleId;
    public String day;
    public String dayInWeek;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResouceId() {
        return resouceId;
    }

    public void setResouceId(String resouceId) {
        this.resouceId = resouceId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    @Override
    public String toString() {
        return "Location{" +
                ", name='" + id + '\'' +
                ", resouceId'" + resouceId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", day='" + day + '\'' +
                ", dayInWeek=" + dayInWeek +
                '}';
    }
}
