package com.victory.hr.attendance.entity;

import com.victory.hr.attendance.enums.CalculateType;
import com.victory.hr.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2017/3/25.
 * Time:14:42
 */
@Entity
@Table(name = "EHR_OverTimeSetting")
public class OverTimeSetting extends BaseEntity<Integer>{

    //偏差打卡时间
    @Column
    private Long offsetTimeUp;

    @Column
    private Long offsetTimeDown;

    //加班时间按登记时间计算还是实际打卡时间计算
    @Column
    private CalculateType calculateType;

    public Long getOffsetTimeUp() {
        return offsetTimeUp;
    }

    public void setOffsetTimeUp(Long offsetTimeUp) {
        this.offsetTimeUp = offsetTimeUp;
    }

    public Long getOffsetTimeDown() {
        return offsetTimeDown;
    }

    public void setOffsetTimeDown(Long offsetTimeDown) {
        this.offsetTimeDown = offsetTimeDown;
    }

    public CalculateType getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(CalculateType calculateType) {
        this.calculateType = calculateType;
    }

}
