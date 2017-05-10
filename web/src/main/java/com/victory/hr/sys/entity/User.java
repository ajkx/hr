package com.victory.hr.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajkx on 2017/5/7.
 */
@Entity
@Table(name = "UserInfo")
public class User extends BaseEntity<Integer> {

    @Column(name = "username",unique = true)
    private String name;

    @Column
    private String password;

    @Column
    private String salt;

    @OneToOne(targetEntity = HrmResource.class)
    private HrmResource hrmResource;

    //该用户的角色集合
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<Role>();
    
    //默认为false 0
    @Column
    private Boolean locked;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public HrmResource getHrmResource() {
        return hrmResource;
    }

    public void setHrmResource(HrmResource hrmResource) {
        this.hrmResource = hrmResource;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getCredentialsSalt() {
        return name + salt;
    }
}
