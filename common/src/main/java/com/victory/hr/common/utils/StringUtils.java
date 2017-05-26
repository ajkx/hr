package com.victory.hr.common.utils;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:10:11
 */
public class StringUtils {

//  用于判断对象是否为空，为空即返回空字符串，主要用于返回前端数据
    public static String nullString(Object object) {
        if (object == null) {
            return "";
        }else{
            return object.toString();
        }
    }

    public static double nullDouble(Object d){
        if (d == null) {
            return 0;
        }else{
            return (double) d;
        }
    }
    public static long nullLong(Object l) {
        if (l == null) {
            return 0;
        }else{
            return (long) l;
        }
    }
}
