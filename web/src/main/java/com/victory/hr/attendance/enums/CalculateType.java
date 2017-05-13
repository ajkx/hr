package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 *
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:54
 */
public enum CalculateType {

    regist("登记"),punch("打卡");

    private final String name;

    private CalculateType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
