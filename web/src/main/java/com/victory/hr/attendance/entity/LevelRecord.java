package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.LevelType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.entity.BaseEntity;
import com.victory.hr.hrm.entity.HrmResource;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 请假记录表
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:29
 */
@Entity
@Table(name = "EHR_LevelRecord")
public class LevelRecord extends BaseEntity<Integer>{


    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resource")
    private HrmResource resource;

    @Column
    private LevelType type;

    @Column(name = "beginDate")
    private Date date;

    @Column(name = "endDate")
    private Date endDate;

    //请假时数
    @Column
    private Long count;

    //请假原因
    @Column
    private String reason;

    //状态
    @Column
    private Status status;

    //备注
    @Column
    private String remark;

    @ManyToMany(targetEntity = AttendanceDetail.class)
    @JoinTable(name = "detail_levelRecord",
            joinColumns = @JoinColumn(name = "record_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id",referencedColumnName = "id"))
    private Set<AttendanceDetail> details;

}
