package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:54
 */
public enum ScheduleType {

    oneSchedule(Constants.oneSchedule),twoSchedule(Constants.twoSchedule),
    threeSchedule(Constants.threeSchedule),restSchedule(Constants.threeSchedule);

    private final String name;

    private ScheduleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
