package com.victory.hr.hrm.entity;

import javax.persistence.*;

/**
 * 技能/语言表
 * Created by ajkx on 2017/2/11.
 */
@Entity
public class SkillInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "language",nullable = false)
    private String language;

    @Column(name = "level")
    private String level;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId",referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    public SkillInfo() {
    }

    public SkillInfo(String language, String level, HrmResource resource) {
        this.language = language;
        this.level = level;
        this.resource = resource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }
}
