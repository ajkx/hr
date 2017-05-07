package com.victory.hr.vo;

/**
 * Created by ajkx
 * Date: 2017/1/17.
 * Time:11:47
 */
public class RoleVo {
    private Integer id;
    private String name;
    private String description;
    private String permissions;
    private String resources;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                ", name'" + name + '\'' +
                ", description='" + description + '\'' +
                ", permissions='" + permissions + '\'' +
                ", resources=" + resources +
                '}';
    }
}
