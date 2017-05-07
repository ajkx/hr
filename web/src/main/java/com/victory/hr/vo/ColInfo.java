package com.victory.hr.vo;

/**
 * Created by ajkx
 * Date: 2017/2/8.
 * Time:12:01
 */
public class ColInfo {
    private String key;
    private String text;
    private String remind;
    private String width;
    private String sorting;
    private String template;
    private String permission;
    public ColInfo() {
    }

    public ColInfo(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public ColInfo(String key, String text,String width,String template) {
        this.key = key;
        this.text = text;
        this.width = width;
    }

    public ColInfo(String key, String text, String template) {
        this.text = text;
        this.key = key;
        this.template = template;
    }

    public ColInfo(String key, String text, String remind, String width, String sorting, String template) {
        this.key = key;
        this.text = text;
        this.remind = remind;
        this.width = width;
        this.sorting = sorting;
        this.template = template;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
