package com.victory.hr.attendance.dao;


import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.common.dao.BaseDao;

import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceScheduleDao extends BaseDao<AttendanceSchedule,Integer> {

    List<AttendanceSchedule> findRestSchedule();
}
