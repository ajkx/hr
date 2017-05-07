package com.victory.hr.util;


import java.lang.reflect.Method;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:16:37
 */
public class ClassUtil {

    /**
     * 利用反射执行动态方法
     * @param object 执行的对象
     * @param method 方法名
     * @param clazz  方法返回值的数据类型
     * @param argument  方法参数
     */
    public static void invokeMethod(Object object,String method,Class clazz,Object argument) {
        Class<?> targetClass = object.getClass();
        try {
            Method mtd = targetClass.getMethod(method, clazz);
            mtd.invoke(object, argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static AttendanceType invokeMethod(Object object,String method){
//        Class targetClass = object.getClass();
//        AttendanceType type = null;
//        try {
//            Method method1 = targetClass.getDeclaredMethod(method);
//            type = (AttendanceType) method1.invoke(object);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return type;
//    }
}
