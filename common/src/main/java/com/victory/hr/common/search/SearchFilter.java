package com.victory.hr.common.search;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:9:03
 */
public class SearchFilter {

    private String key;

    private Object value;

    private String operator;

    private Class[] clazz;

    private Object[] objects;

    public SearchFilter() {
    }

    public SearchFilter(String key, Object value, String operator) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Class[] getClazz() {
        return clazz;
    }

    public void setClazz(Class[] clazz) {
        this.clazz = clazz;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
