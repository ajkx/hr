package com.victory.hr.attendance.service;

import com.victory.hr.attendance.dao.DateRecordDao;
import com.victory.hr.attendance.entity.CustomHoliday;
import com.victory.hr.attendance.entity.DateRecord;
import com.victory.hr.common.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by ajkx
 * Date: 2017/4/22.
 * Time:16:14
 */
@Service
public class DateRecordService extends BaseService<DateRecord,Integer> {

    private DateRecordDao getDao() {
        return (DateRecordDao) baseDao;
    }

    public DateRecord getTopRecord() {
        return getDao().getTopRecord();
    }
}
