package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.OverTimeType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/10.
 * Time:18:48
 */
@Entity
@Table(name = "EHR_OverTimeRecord")
public class OverTimeRecord extends BaseEntity<Integer>{


    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    //1代表平时加班 2代表周末加班 3代表节日加班
    @Column
    private OverTimeType type;

//    加班开始日期 时间
    @Column(name = "beginDate")
    private Date beginDate;

    //加班结束日期 时间
    @Column(name = "endDate")
    private Date endDate;

    //实际上班打卡时间
    @Column(name = "actualBeginDate")
    private Date actualBeginDate;

    //实际下班打卡时间
    @Column(name = "actualEndDate")
    private Date actialEndDate;

//    计划加班时数
    @Column
    private Long totalTime;

//    实际加班时数
    @Column
    private Long actualTotalTime;

    //加班原因
    @Column
    private String reason;

    //状态
    @Column
    private Status status;

    //备注
    @Column
    private String remark;

    //是否连班
    @Column
    private Boolean isLink;

    @ManyToOne(targetEntity = AttendanceDetail.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private AttendanceDetail detail;


}
