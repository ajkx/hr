package com.victory.hr.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 自定义集合工具类
 *
 * @author ajkx_Du
 * @create 2016-11-23 10:11
 */
public class CollectionUtil {

    public static LinkedHashMap getObjectFields(Class clazz){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        Field[] fs = clazz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (int i = 0;i < fs.length;i++) {
            Field f = fs[i];
            f.setAccessible(true);
            if(f.getName().equals("id")){
                continue;
            }
            map.put(f.getName(), "");
        }
        return map;
    }

    public static LinkedHashMap getObejctValueAndFields(Object obj) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        Field[] fs = obj.getClass().getDeclaredFields();
        for (Field field : fs) {
            boolean accessFlag = field.isAccessible();
            String varname = field.getName();
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                if (o != null) {
                    map.put(varname, o.toString());
                }
                field.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
