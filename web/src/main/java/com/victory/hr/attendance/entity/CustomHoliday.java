package com.victory.hr.attendance.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.net.Inet4Address;
import java.sql.Date;

/**
 * Created by ajkx
 * Date: 2017/4/22.
 * Time:16:04
 */
@Entity
@Table(name = "EHR_CustomHoliday")
public class CustomHoliday extends BaseEntity<Integer>{

    @Column
    private String name;

    @Column(unique = true)
    private Date date;

    @Column
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;

        if(obj != null && obj.getClass() == CustomHoliday.class){
            return this.date == ((CustomHoliday)obj).getDate();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.date.hashCode() * 11;
    }

}
