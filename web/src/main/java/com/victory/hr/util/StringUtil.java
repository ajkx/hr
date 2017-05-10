package com.victory.hr.util;


import com.victory.hr.common.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author ajkx_Du
 * @create 2016-10-21 16:12
 */
public class StringUtil {

    public static int[] getIds(String str) {
        String[] temp = str.split(",");
        int[] tempint = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            tempint[i] = Integer.parseInt(temp[i]);
        }
        return tempint;
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static String nullString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static long nullLong(Object num){
        if (num == null) {
            return 0;
        } else {
            return (long)num;
        }
    }

    public static int nullInteger(Object num){
        if (num == null) {
            return 0;
        } else {
            return (int)num;
        }
    }
    public static float nullFloat(Object num){
        if (num == null) {
            return 0;
        } else {
            return (float)num;
        }
    }

    public static Double nullDouble(Object num){
        if (num == null) {
            return 0D;
        } else {
            return (Double)num;
        }
    }

    public static boolean nullBoolean(Object num){
        if (num == null) {
            return false;
        } else {
            return (boolean)num;
        }
    }

    public static String getScript(String topic, String prop, boolean hasPermission) {
        if (hasPermission) {
            return "return \"<a href='javascript:void(0)' onclick=\\\"showEditModal('/" + topic + "/view/\"+" + prop + ".id+\"')\\\" class='font-color'>\"+" + prop + ".name+\"</a>\";";
        } else {
            return "return \"<a disable='' href='javascript:void(0)' onclick=\\\"showEditModal('/" + topic + "/view/\"+" + prop + ".id+\"')\\\" class='font-color'>\"+" + prop + ".name+\"</a>\";";
        }
    }

    public static String getMultiScript(String topic, String prop, boolean hasPermission) {
        if (hasPermission) {
            return "var el = '';" +
                    "$(" + prop + ").each(function(index,element){" +
                    "el += \"<a href='javascript:void(0)' onclick=\\\"showEditModal('/" + topic + "/view/\"+element.id+\"')\\\" class='font-color'>\"+element.name+\"</a>&nbsp;&nbsp;\";});return el;";
        } else {
            return "var el = '';" +
                    "$(" + prop + ").each(function(index,element){" +
                    "el += \"<a disable='' href='javascript:void(0)' onclick=\\\"showEditModal('/" + topic + "/view/\"+element.id+\"')\\\" class='font-color'>\"+element.name+\"</a>&nbsp;&nbsp;\";});return el;";

        }
    }

    public static String subString(Object object) {
        String str = object.toString();
        return str.substring(0, 5);
    }

    public static String getWeek(int i) {
        String week = "";
        switch (i) {
            case 0:
                week = "周一";
                break;
            case 1:
                week = "周二";
                break;
            case 2:
                week = "周三";
                break;
            case 3:
                week = "周四";
                break;
            case 4:
                week = "周五";
                break;
            case 5:
                week = "周六";
                break;
            case 6:
                week = "周日";
                break;
        }
        return week;
    }

//    public static String getScheduleTime(AttendanceSchedule schedule) {
//        String value = "";
//        switch (schedule.getScheduleType()) {
//            case 1:
//                value = StringUtil.subString(schedule.getFirst_time_up()) + "-" + StringUtil.subString(schedule.getFirst_time_down());
//                break;
//            case 2:
//                value = StringUtil.subString(schedule.getFirst_time_up()) + "-" + StringUtil.subString(schedule.getFirst_time_down())
//                        + "&nbsp;&nbsp;" + StringUtil.subString(schedule.getSecond_time_up()) + "-" + StringUtil.subString(schedule.getSecond_time_down());
//                break;
//            case 3:
//                value = StringUtil.subString(schedule.getFirst_time_up()) + "-" + StringUtil.subString(schedule.getFirst_time_down())
//                        + "&nbsp;&nbsp;" + StringUtil.subString(schedule.getSecond_time_up()) + "-" + StringUtil.subString(schedule.getSecond_time_down())
//                        + "&nbsp;&nbsp;" + StringUtil.subString(schedule.getThird_time_up()) + "-" + StringUtil.subString(schedule.getThird_time_down());
//                break;
//        }
//        return value;
//    }

    public static <T> List<T> splitForList(BaseService baseService, String str, Class<T> entityClass){
        String[] array = str.split(",");
        List<T> list = new ArrayList<T>();
        for (String temp : array) {
            if(temp.equals("")) continue;
                T entity = (T)baseService.findOne(Integer.parseInt(temp));
                if (entity != null) {
                    list.add(entity);
                }
        }
        return list;
    }


}
