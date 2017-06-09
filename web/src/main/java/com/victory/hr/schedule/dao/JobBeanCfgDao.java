package com.victory.hr.schedule.dao;



import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.schedule.entity.JobBeanCfg;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface JobBeanCfgDao extends BaseDao<JobBeanCfg,Integer> {

    JobBeanCfg findByJobClass(String jobClass);
}
