package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:54
 */
public enum Status {
    normal(Constants.normal),abnormal(Constants.abnormal),
    missing(Constants.missing),calculate(Constants.calculate),
    error(Constants.error);

    private final String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
