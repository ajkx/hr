package com.victory.hr.attendance.dao;


import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmResource;

import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface LevelRecordDao extends BaseDao<LevelRecord,Integer> {

    List<LevelRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource);
}
