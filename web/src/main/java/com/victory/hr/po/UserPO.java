package com.victory.hr.po;

import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;

import java.util.Set;

/**
 * 用于接受json解析的参数
 * Created by ajkx
 * Date: 2017/5/10.
 * Time:10:09
 */
public class UserPO {

    private User user;

//    private int[] roles;
    private Set<Role> roles;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    //
//    public int[] getRoles() {
//        return roles;
//    }
//
//    public void setRoles(int[] roles) {
//        this.roles = roles;
//    }
}
