package com.victory.hr.vo;

/**
 * Created by ajkx
 * Date: 2017/3/9.
 * Time:16:05
 */
public class JsonTreeState {


    //是否可选
    private boolean checked;

    //是否可用
    private boolean disabled;

    //是否展开
    private boolean expanded;

    //是否选择
    private boolean selected;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
