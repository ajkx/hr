package com.victory.hr.attendance.dao;


import com.victory.hr.attendance.entity.AttendanceRecord;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmResource;

import java.sql.Date;
import java.util.List;


/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceRecordDao extends BaseDao<AttendanceRecord,Integer> {

    List<AttendanceRecord> findByResourceAndDate(HrmResource resource, java.util.Date beginDate, java.util.Date endDate);
}
