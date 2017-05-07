package com.victory.hr.hrm.entity;

/**
 * Created by ajkx on 2017/5/1.
 */
public enum ChangeType {

    transfer("调动"),dismiss("离职"),turnOfficial("转正"),reEmploy("返聘");
    private final String type;

    private ChangeType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
