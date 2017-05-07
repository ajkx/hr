package com.victory.hr.sys.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by ajkx on 2017/5/7.
 */
@Entity
public class Module extends BaseEntity<Long>{


    @Column
    private String name;

    @Column
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
