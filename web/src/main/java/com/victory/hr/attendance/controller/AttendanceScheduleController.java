package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.attendance.service.AttendanceGroupService;
import com.victory.hr.attendance.service.AttendanceScheduleService;
import com.victory.hr.base.BaseCURDController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/schedule")
public class AttendanceScheduleController extends BaseCURDController<AttendanceSchedule,Integer>{

    private AttendanceScheduleService getService() {
        return (AttendanceScheduleService) baseService;
    }

    public AttendanceScheduleController() {
        setResourceIdentity("AttendanceSchedule");
    }

    @Override
    protected void setCommonData(Model model,AttendanceSchedule schedule) {
        if (schedule != null) {
            Set<AttendanceGroup> groups = new HashSet<>();
            groups.addAll(schedule.getMondays());
            groups.addAll(schedule.getTuesdays());
            groups.addAll(schedule.getWednesdays());
            groups.addAll(schedule.getThursdays());
            groups.addAll(schedule.getFridays());
            groups.addAll(schedule.getSaturdays());
            groups.addAll(schedule.getSundays());
            StringBuilder sb = new StringBuilder();
            if(groups.size() > 0){
                for (AttendanceGroup group : groups) {
                    sb.append(group.getName());
                    sb.append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                model.addAttribute("size", groups.size());
                model.addAttribute("group", sb.toString());
            }

            int type = schedule.getScheduleType().ordinal();
            model.addAttribute("scheduleType", type);
        }
    }

    /**
     * 选择班次列表的模态框
     * @param type
     * @param model
     * @return
     */
    @RequiresPermissions(value = "AttendanceSchedule:view")
    @RequestMapping(value = "/modal/{type}")
    public String modal_list(@PathVariable String type, Model model) {
        List<AttendanceSchedule> list = getService().findAll();
        List<Map<String, String>> mapList = new ArrayList<>();
        for (AttendanceSchedule schedule : list) {
            Map<String, String> temp = new HashMap<>();
            if(schedule.getRest() != null && schedule.getRest())continue;
            temp.put("id", schedule.getId()+"");
            temp.put("name", schedule.getName());
            temp.put("time", getService().getScheduleTime(schedule));
            mapList.add(temp);
        }
        model.addAttribute("list", mapList);
        if(type.equals("multi")){
            return viewName("multiList");
        }else{
            return viewName("singleList");
        }

    }

}
