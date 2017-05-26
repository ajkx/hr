package com.victory.hr.attendance.service;

import com.victory.hr.attendance.dao.AttendanceScheduleDao;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class AttendanceScheduleService extends BaseService<AttendanceSchedule,Integer> {

    private AttendanceScheduleDao getDao() {
        return (AttendanceScheduleDao) baseDao;
    }
    @Override
    public AttendanceSchedule save(AttendanceSchedule entity) {
        initData(entity);
        return super.save(entity);
    }

    @Override
    public void update(AttendanceSchedule entity) {
        initData(entity);
        super.update(entity);
    }

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<AttendanceSchedule> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (AttendanceSchedule schedule : list) {
            if(schedule.getRest() != null && schedule.getRest())continue;
            Map<String, Object> map = new HashMap<>();
            map.put("id", schedule.getId());
            map.put("name", StringUtils.nullString(schedule.getName()));
            String time = getScheduleTime(schedule);
            map.put("time", time);
            map.put("totalTime", schedule.getAttendanceTime());
            map.put("description", StringUtils.nullString(schedule.getDescription()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public String getScheduleTime(AttendanceSchedule schedule) {
        String value = "";
        switch (schedule.getScheduleType().ordinal()) {
            case 0:
                value = schedule.getFirst_time_up().toString().substring(0,5) + "-" + schedule.getFirst_time_down().toString().substring(0,5);
                break;
            case 1:
                value = schedule.getFirst_time_up().toString().substring(0,5) + "-" + schedule.getFirst_time_down().toString().substring(0,5)
                        + "&nbsp;&nbsp;" + schedule.getSecond_time_up().toString().substring(0,5) + "-" + schedule.getSecond_time_down().toString().substring(0,5);
                break;
            case 2:
                value = schedule.getFirst_time_up().toString().substring(0,5) + "-" + schedule.getFirst_time_down().toString().substring(0,5)
                        + "&nbsp;&nbsp;" + schedule.getSecond_time_up().toString().substring(0,5) + "-" + schedule.getSecond_time_down().toString().substring(0,5)
                        + "&nbsp;&nbsp;" +schedule.getThird_time_up().toString().substring(0,5) + "-" + schedule.getThird_time_down().toString().substring(0,5);
                break;
        }
        return value;
    }

    public void initData(AttendanceSchedule schedule) {
        schedule.setAttendanceTime(schedule.getAttendanceTime()*1000);
        schedule.setScope_up(schedule.getScope_up()*60*1000);
        schedule.setScope_down(schedule.getScope_down()*60*1000);
        schedule.setOffsetTime(schedule.getOffsetTime()*60*1000);
        schedule.setSimplename(schedule.getName().substring(0,1));
        schedule.setRest(false);
    }

    public AttendanceSchedule findRestSchedule() {
        return getDao().findRestSchedule().get(0);
    }
}
