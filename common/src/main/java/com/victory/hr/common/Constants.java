package com.victory.hr.common;

/**
 * Created by ajkx
 * Date: 2017/5/9.
 * Time:12:05
 */
public interface Constants {

    /**
     * 操作名称
     */
    String OP_NAME = "op";

    // ======== 出勤类别相关========
    String normal = "正常";

    String abnormal = "异常";

    String missing = "不在考勤组";

    String calculate = "待计算";

    String error = "作废";

// ======== 考勤组类别 =========

    String fixed = "固定班制";

    String arrange = "排班制";

    String free = "自由打卡";

    String special = "特殊班次";

// ======== 班次类别 =========

    String oneSchedule = "一天一次上下班";

    String twoSchedule = "一天二次上下班";

    String threeSchedule = "一天三次上下班";

    String restSchedule = "休息班次";

// ======== 打卡记录类型 =========

    String machine = "考勤机读取";

    String manual = "签卡";

// ======== 请假类型 ==========

    String personal = "事假";

    String rest = "调休";

    String sick = "病假";

    String business = "出差";

    String injury = "工伤";

    String delivery  = "产假";

    String married = "婚假";

    String funeral = "丧假";

    String annual = "年假";

// ======== 加班类型 =========

    String ot_normal = "平时加班";

    String ot_weekend = "周末加班";

    String ot_festival = "节日加班";

// ========= 出勤类别 ========

    String AD_normal = "正常";

    String AD_late = "迟到";

    String AD_early = "早退";

    String AD_level = "请假";

    String AD_rest = "休息";
}
