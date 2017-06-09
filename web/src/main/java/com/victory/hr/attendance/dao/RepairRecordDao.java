package com.victory.hr.attendance.dao;


import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.attendance.entity.RepairRecord;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmResource;

import java.sql.Date;
import java.util.List;

/**
 * 异常记录DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface RepairRecordDao extends BaseDao<RepairRecord,Integer> {

    List<RepairRecord> findByStatus(Status status);

    List<RepairRecord> findByHrmResourceAndDate(HrmResource resource, Date date);
}
