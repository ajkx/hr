package com.victory.hr.attendance.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ajkx on 2017/2/27.
 */
@Entity
@Table(name = "EHR_DateRecord")
public class DateRecord extends BaseEntity<Integer>{

    @Column
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
