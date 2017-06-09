package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:18:53
 */
public enum AttendanceType {
    normal(Constants.AD_normal),late(Constants.AD_late),
    early(Constants.AD_early),level(Constants.AD_level),
    miss(Constants.AD_miss), rest(Constants.AD_rest),
    lack(Constants.AD_lack),personal("事假"),
    business("出差"),injury("工伤"),
    delivery("产假"),married("婚假"),
    funeral("丧假"),annual("年假");

    private final String name;

    private AttendanceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
