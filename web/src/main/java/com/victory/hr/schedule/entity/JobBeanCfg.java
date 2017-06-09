package com.victory.hr.schedule.entity;

import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ajkx
 * Date: 2017/6/2.
 * Time:16:03
 */
@Entity
@Table(name = "EHR_JobBeanCfg")
public class JobBeanCfg extends BaseEntity<Integer>{

    private static final long serialVersionUID = 1243258731231L;

    private String jobClass;

    private String cronExpression;

    private Boolean autoStart = Boolean.TRUE;

    private Boolean runHistory = Boolean.TRUE;

    private String description;

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getAutoStart() {
        return autoStart;
    }

    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
    }

    public Boolean getRunHistory() {
        return runHistory;
    }

    public void setRunHistory(Boolean runHistory) {
        this.runHistory = runHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
