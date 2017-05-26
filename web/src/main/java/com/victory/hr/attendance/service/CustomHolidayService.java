package com.victory.hr.attendance.service;

import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.dao.CustomHolidayDao;
import com.victory.hr.attendance.entity.CustomHoliday;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/4/22.
 * Time:16:14
 */
@Service
public class CustomHolidayService extends BaseService<CustomHoliday,Integer> {

    private CustomHolidayDao getDao() {
        return (CustomHolidayDao) baseDao;
    }

    public Set<Date> findAllDate() {
        List<CustomHoliday> customHolidays = findAll();
        Set<Date> dateSet = new HashSet<>();
        for (CustomHoliday holiday : customHolidays) {
            dateSet.add(holiday.getDate());
        }
        return dateSet;
    }

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = baseDao.findAll(searchable);
        List<CustomHoliday> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (CustomHoliday holiday : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", holiday.getId());
            map.put("name", holiday.getName());
            map.put("date", holiday.getDate());
            map.put("description", holiday.getDescription());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

}
