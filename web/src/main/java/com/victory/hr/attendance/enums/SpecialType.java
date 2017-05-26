package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/22.
 * Time:10:11
 */
public enum SpecialType {
    mustPunch("必须打卡"),noPunch("不用打卡");

    private final String name;

    private SpecialType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
