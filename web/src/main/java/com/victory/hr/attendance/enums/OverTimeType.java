package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:15:50
 */
public enum OverTimeType {
    ot_normal(Constants.ot_normal),ot_festival(Constants.ot_festival),
    ot_weekend(Constants.ot_weekend);

    private final String name;

    private OverTimeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
