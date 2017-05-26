package com.victory.hr.attendance.service;

import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.service.HrmResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ajkx on 2017/2/16.
 */
@Service
public class AttendanceGroupService extends BaseService<AttendanceGroup,Integer> {

    @Autowired
    private HrmResourceService resourceService;

    @Autowired
    private AttendanceScheduleService scheduleService;

    @Override
    public AttendanceGroup save(AttendanceGroup entity) {
        initData(entity);
        //由于 双向 1 - N关联 从1的一端来控制会导致 N可能有重复数据产生导致报错，所以在save时要先将对应resource的groupID删除，在重新insert,性能不好
        Set<HrmResource> resourceSet = entity.getResources();
        Set<HrmResource> resourceSet1 = new HashSet<>();
        for(HrmResource resource : resourceSet){
            HrmResource temp = resourceService.findOne(resource.getId());
            temp.setAttendanceGroup(null);
        }
        super.save(entity);

        return entity;
    }

    @Override
    public void update(AttendanceGroup entity) {
        initData(entity);
//        从N的一端控制要先持久化对应的resource
        Set<HrmResource> resourceSet = entity.getResources();
        Set<HrmResource> resourceSet1 = new HashSet<>();
        for(HrmResource resource : resourceSet){
            HrmResource temp = resourceService.findOne(resource.getId());
            temp.setAttendanceGroup(entity);
        }
        super.update(entity);

    }

    public void initData(AttendanceGroup attendanceGroup) {
        AttendanceSchedule schedule = scheduleService.findRestSchedule();
        if(attendanceGroup.getMonday() == null){
            attendanceGroup.setMonday(schedule);
        }
        if (attendanceGroup.getTuesday() == null) {
            attendanceGroup.setTuesday(schedule);
        }
        if (attendanceGroup.getWednesday() == null) {
            attendanceGroup.setWednesday(schedule);
        }
        if (attendanceGroup.getThursday() == null) {
            attendanceGroup.setThursday(schedule);
        }
        if (attendanceGroup.getFriday() == null) {
            attendanceGroup.setFriday(schedule);
        }
        if (attendanceGroup.getSaturday() == null) {
            attendanceGroup.setSaturday(schedule);
        }
        if (attendanceGroup.getSunday() == null) {
            attendanceGroup.setSunday(schedule);
        }
    }
    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<AttendanceGroup> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (AttendanceGroup group : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", group.getId());
            map.put("name", StringUtils.nullString(group.getName()));
            map.put("description", StringUtils.nullString(group.getDescription()));

            String type = "";
            StringBuilder builder = new StringBuilder();
            switch (group.getGroupType().name()){
                case "fixed":
                    type = "固定班制";
                    List<AttendanceSchedule> temp = new ArrayList<>();
                    temp.add(group.getMonday());
                    temp.add(group.getTuesday());
                    temp.add(group.getWednesday());
                    temp.add(group.getThursday());
                    temp.add(group.getFriday());
                    temp.add(group.getSaturday());
                    temp.add(group.getSunday());
                    LinkedHashMap<AttendanceSchedule, String> tempMap = new LinkedHashMap<>();
                    for(int i = 0; i < temp.size(); i++) {
                        String value = "";
                        for (AttendanceSchedule key : tempMap.keySet()) {
                            if (key.getName().equals(temp.get(i).getName())) {
                                value = tempMap.get(key) + "&nbsp&nbsp" + getWeek(i).substring(1, 2);
                            }
                        }
                        tempMap.put(temp.get(i), value.equals("") ? getWeek(i) + "" : value);
                    }
                    for (AttendanceSchedule key : tempMap.keySet()) {
                        builder.append("每"+tempMap.get(key)+"&nbsp;&nbsp;&nbsp;");
                        if(key.getRest() != null && key.getRest()){
                            builder.append(key.getName()+"</br>");
                        }else {
                            builder.append(key.getName() + ":" + scheduleService.getScheduleTime(key)+"</br>");
                        }
                    }
                    break;
                case "arrange":
                    type = "排班制";
                    for (AttendanceSchedule schedule : group.getSchedules()) {
                        builder.append(schedule.getName()+":"+scheduleService.getScheduleTime(schedule)+"</br>");
                    }
                    break;
                case "free":
                    type = "自由打卡";
                    break;
            }
            map.put("type",type);
            map.put("time", builder.toString());
            map.put("count", group.getResources().size());

            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public  String getWeek(int i) {
        String week = "";
        switch (i) {
            case 0:
                week = "周一";
                break;
            case 1:
                week = "周二";
                break;
            case 2:
                week = "周三";
                break;
            case 3:
                week = "周四";
                break;
            case 4:
                week = "周五";
                break;
            case 5:
                week = "周六";
                break;
            case 6:
                week = "周日";
                break;
        }
        return week;
    }
}

