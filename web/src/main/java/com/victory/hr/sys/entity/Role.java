package com.victory.hr.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajkx on 2017/5/7.
 */
@Entity
@Table(name = "EHR_Role")
public class Role extends BaseEntity<Integer>{

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(targetEntity = Resource.class,fetch = FetchType.LAZY)
    @JoinTable(name = "EHR_role_resource",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    private Set<Resource> resources = new HashSet<Resource>();


    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "EHR_user_role",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<User>();

    //是否启用
    @Column
    private Boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//    @JsonBackReference
    public Set<Resource> getResources() {
        return resources;
    }
//    @JsonBackReference
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
//    @JsonBackReference
    public Set<User> getUsers() {
        return users;
    }
//    @JsonBackReference
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
