package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.dao.OverTimeRecordDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.entity.HrmResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
@Repository
public class OverTimeRecordDaoImpl extends BaseDaoImpl<OverTimeRecord,Integer> implements OverTimeRecordDao{

    //    获取加班结束日期小于等于endDate并且为待计算的数据，防止跨天加班计算错误，保证结束日期有打卡数据
    @Override
    public List<OverTimeRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate, HrmResource resource) {
        return find("select r from OverTimeRecord r where date between ?0 and ?1 and resource = ?2 and status = 3",beginDate, endDate, resource);
    }
}
