package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.dao.LevelRecordDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.entity.HrmResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
@Repository
public class LevelRecordDaoImpl extends BaseDaoImpl<LevelRecord,Integer> implements LevelRecordDao{

    /**
     * 只找寻有时间交集的请假记录，即不管是否已经计算或者作废之类的状态
     * @param beginDate
     * @param endDate
     * @param resource
     * @return
     */
    @Override
    public List<LevelRecord> findByDateAndResource(Date beginDate, Date endDate, HrmResource resource) {
        return find("select r from LevelRecord r where resource = ?0 and date between ?1 and ?2 or endDate between ?1 and ?2 " +
                "or ?1 between date and endDate or ?2 between date and endDate",resource,beginDate,endDate);
    }
}
