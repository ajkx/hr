package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.dao.AttendanceScheduleDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
@Repository
public class AttendanceScheduleDaoImpl extends BaseDaoImpl<AttendanceSchedule,Integer> implements AttendanceScheduleDao{
}
