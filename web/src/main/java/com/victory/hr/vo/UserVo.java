package com.victory.hr.vo;


import com.victory.hr.hrm.entity.HrmResource;

/**
 * Created by ajkx
 * Date: 2017/1/17.
 * Time:11:47
 */
public class UserVo {
    private Integer id;
    private String name;
    private String password;
    private String password_confirm;
    private HrmResource resourceid;
    private String roleids;
    private Boolean isLock = false;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public HrmResource getResourceid() {
        return resourceid;
    }

    public void setResourceid(HrmResource resourceid) {
        this.resourceid = resourceid;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                ", name'" + name + '\'' +
                ", resourceid='" + resourceid + '\'' +
                ", password='" + password + '\'' +
                ", password_confirm=" + password_confirm +
                ", roleids=" + roleids +
                '}';
    }
}
