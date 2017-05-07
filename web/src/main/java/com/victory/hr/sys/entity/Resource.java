package com.victory.hr.sys.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajkx on 2017/5/7.
 */
@Entity
public class Resource extends BaseEntity<Long>{

    public static enum ResourceType {
        menu("菜单"), button("按钮");
        private final String info;

        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ResourceType type = ResourceType.menu;

    @ManyToOne(targetEntity = Module.class)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;

    @Column
    private String permission;

    @Column
    private String url;

    @ManyToMany(targetEntity = Role.class,fetch = FetchType.LAZY)
    @JoinTable(name = "role_resource",
            joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<Role>();

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

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
