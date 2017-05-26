package com.victory.hr.attendance.dao;


import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmResource;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface OverTimeRecordDao extends BaseDao<OverTimeRecord,Integer> {

    List<OverTimeRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource);
}
