package com.victory.hr.attendance.enums;

import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:14:54
 */
public enum GroupType {

    fixed(Constants.fixed),arrange(Constants.arrange),
    free(Constants.free),special(Constants.special);

    private final String name;

    private GroupType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
