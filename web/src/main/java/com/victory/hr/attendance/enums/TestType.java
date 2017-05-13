package com.victory.hr.attendance.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.victory.hr.common.Constants;
import com.victory.hr.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:15:50
 */
public enum TestType {
    machine,manual;

    private static Map<String, TestType> maps = new HashMap<>(3);

    static{
        maps.put("machine", machine);
        maps.put("manual", manual);
    }
    @JsonCreator
    public static TestType getValue(String value) {
        System.out.println("aaa");
        return maps.get(org.apache.commons.lang3.StringUtils.lowerCase(value));
    }

    @JsonValue
    public String toValue() {
        for (Map.Entry<String, TestType> entry : maps.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }

        return null; // or fail
    }
}
