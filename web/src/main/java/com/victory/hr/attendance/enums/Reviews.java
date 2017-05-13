package com.victory.hr.attendance.enums;

import java.io.Serializable;

/**
 * Created by ajkx
 * Date: 2017/5/13.
 * Time:11:05
 */
public class Reviews implements Serializable {

    private Integer id;
    private Reos reos;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Reos getReos() {return reos;}

    public void setReos(Reos reos) {
        this.reos = reos;
    }
}