package com.victory.hr.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;

/**
 * 给 Spring MVC 返回Json专用的Vo对象
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonVo extends HashMap<String, Object> {

    public static final String STATUS = "status";
    public static final String MSG = "msg";

    public Boolean getStatus() {
        return (Boolean) get(STATUS);
    }

    public JsonVo setStatus(Boolean status) {
        put(STATUS, status);
        return this;
    }

    public String getMsg() {
        return (String) get(MSG);
    }

    public JsonVo setMsg(String msg) {
        put(MSG, msg);
        return this;
    }

    public JsonVo addValue(String key, Object value) {
        put(key, value);
        return this;
    }

}
