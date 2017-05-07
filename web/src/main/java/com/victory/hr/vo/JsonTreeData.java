package com.victory.hr.vo;

import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-11-23 16:43
 */
public class JsonTreeData {

    //显示文本
    private String text;
    //显示图标
    private String icon;
    //链接
    private String href;
    //下级元素
    private List<JsonTreeData> nodes;

    //状态信息
    private JsonTreeState state;

    //右边标记
    private List<String> tags;

    //是否可选择
    private boolean selectable = true;

    public JsonTreeData() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<JsonTreeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<JsonTreeData> nodes) {
        this.nodes = nodes;
    }

    public JsonTreeState getState() {
        return state;
    }

    public void setState(JsonTreeState state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
}
