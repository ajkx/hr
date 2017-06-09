package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.OverTimeSettingDao;
import com.victory.hr.attendance.dao.RepairRecordDao;
import com.victory.hr.attendance.entity.OverTimeSetting;
import com.victory.hr.attendance.entity.RepairRecord;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.entity.HrmResource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * 异常DAO接口
 *
 * @author ajkx_Du
 * @create 2017-6-08 15:22
 */
@Repository
public class RepairRecordDaoImpl extends BaseDaoImpl<RepairRecord,Integer> implements RepairRecordDao {

    @Override
    public List<RepairRecord> findByStatus(Status status) {
        return find("select r from RepairRecord where status = ?0",status);
    }

    @Override
    public List<RepairRecord> findByHrmResourceAndDate(HrmResource resource,Date date) {
        return find("select r from RepairRecord where resource = ?0 and date = ?1",resource,date);
    }
}
