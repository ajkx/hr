package com.victory.hr.attendance.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.victory.hr.common.Constants;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:15:50
 */
public enum RecordType {
    machine(Constants.machine),manual(Constants.manual);

    private final String name;

    private RecordType(String name) {
        this.name = name;
    }

//    @JsonCreator
//    public static RecordType getType(int id) {
//        for (RecordType type : values()) {
//            if (type.getId() == id) {
//                return type;
//            }
//        }
//        return null;
//    }


    public String getName() {
        return name;
    }


}
