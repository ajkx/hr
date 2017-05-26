package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.dao.AttendanceRecordDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceRecord;
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
public class AttendanceRecordDaoImpl extends BaseDaoImpl<AttendanceRecord,Integer> implements AttendanceRecordDao{

    @Override
    public List<AttendanceRecord> findByResourceAndDate(HrmResource resource, java.util.Date beginDate, java.util.Date endDate) {
        return find("select a from AttendanceRecord a where resource = ?0 and date between ?1 and ?2", resource, beginDate,endDate);
    }
}
