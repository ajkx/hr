package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:54
 */
public enum LevelType {

    personal(Constants.personal),rest(Constants.restSelf),
    sick(Constants.sick),business(Constants.business),
    injury(Constants.injury),delivery(Constants.delivery),
    married(Constants.married),funeral(Constants.funeral),
    annual(Constants.annual);

    private final String name;

    private LevelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
