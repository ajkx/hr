package com.victory.hr.hrm.entity;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:10:45
 */
public enum  HrmStatus {
    /**
     * 试用，正式，临时，试用延期，解聘，离职，退休，无效
     */
    probation("试用"),offical("正式"),
    temporary("临时"),late_probation("试用延期"),
    dismiss("解聘"),dismiss_self("离职"),
    retrie("退休"),invalid("无效");

    private final String name;

    private HrmStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
