package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.attendance.entity.SpecialDate;
import com.victory.hr.attendance.enums.SpecialType;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.attendance.service.AttendanceGroupService;
import com.victory.hr.attendance.service.AttendanceScheduleService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.util.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/group")
public class AttendanceGroupController extends BaseCURDController<AttendanceGroup, Integer> {

    @Autowired
    private AttendanceScheduleService scheduleService;

    private AttendanceGroupService getService() {
        return (AttendanceGroupService) baseService;
    }

    public AttendanceGroupController() {
        setResourceIdentity("AttendanceGroup");
    }

    @Override
    protected void setCommonData(Model model, AttendanceGroup group) {
        if (group != null) {

            AttendanceSchedule monday = group.getMonday();
            if (monday != null && (monday.getRest() == null || !monday.getRest())) {
                model.addAttribute("monday", monday.getName() + ":" + scheduleService.getScheduleTime(monday));
            }

            AttendanceSchedule tuesday = group.getTuesday();
            if (tuesday != null && (tuesday.getRest() == null || !tuesday.getRest())) {
                model.addAttribute("tuesday", tuesday.getName() + ":" + scheduleService.getScheduleTime(tuesday));
            }

            AttendanceSchedule wednesday = group.getWednesday();
            if (wednesday != null && (wednesday.getRest() == null || !wednesday.getRest())) {
                model.addAttribute("wednesday", wednesday.getName() + ":" + scheduleService.getScheduleTime(wednesday));
            }

            AttendanceSchedule thursday = group.getThursday();
            if (thursday != null && (thursday.getRest() == null || !thursday.getRest())) {
                model.addAttribute("thursday", thursday.getName() + ":" + scheduleService.getScheduleTime(thursday));
            }

            AttendanceSchedule friday = group.getFriday();
            if (friday != null && (friday.getRest() == null || !friday.getRest())) {
                model.addAttribute("friday", friday.getName() + ":" + scheduleService.getScheduleTime(friday));
            }

            AttendanceSchedule saturday = group.getSaturday();
            if (saturday != null && (saturday.getRest() == null || !saturday.getRest())) {
                model.addAttribute("saturday", saturday.getName() + ":" + scheduleService.getScheduleTime(saturday));
            }

            AttendanceSchedule sunday = group.getSunday();
            if (sunday != null && (sunday.getRest() == null || !sunday.getRest())) {
                model.addAttribute("sunday", sunday.getName() + ":" + scheduleService.getScheduleTime(sunday));
            }

            Set<SpecialDate> specialDateSet = group.getSpecialDates();
            List<Map<String, Object>> list = new ArrayList<>();
            List<Map<String, Object>> list2 = new ArrayList<>();
            int s1 = 0;
            int s2 = 0;
            for (SpecialDate date : specialDateSet) {
                if(date.getSpecialType() == SpecialType.mustPunch){
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("id", date.getId());
                    temp.put("date", date.getDate());
                    temp.put("scheduleId", date.getSchedule().getId());
                    temp.put("scheduleName", date.getSchedule().getName() + " : "+ scheduleService.getScheduleTime(date.getSchedule()));
                    list.add(temp);
                    s1++;
                } else if (date.getSpecialType() == SpecialType.noPunch) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("id", date.getId());
                    temp.put("date", date.getDate());
                    list2.add(temp);
                    s2++;
                }
            }
            model.addAttribute("punchList", list);
            model.addAttribute("punchSize", s1);
            model.addAttribute("notList", list2);
            model.addAttribute("notSize", s2);
        }
    }

    @RequiresPermissions(value = "AttendanceGroup:create")
    @RequestMapping(value = "modal/punch")
    public String showPunchModal(Model model) {
        model.addAttribute("date", DateUtil.getToday());
        List<AttendanceSchedule> list = scheduleService.findAll();
        List<Map<String, String>> mapList = new ArrayList<>();
        for (AttendanceSchedule schedule : list) {
            Map<String, String> temp = new HashMap<>();
            if(schedule.getRest() != null && schedule.getRest())continue;
            temp.put("id", schedule.getId()+"");
            temp.put("name", schedule.getName());
            temp.put("time", scheduleService.getScheduleTime(schedule));
            mapList.add(temp);
        }
        model.addAttribute("list", mapList);
        return viewName("/modal/mustPunch");
    }

    @RequiresPermissions(value = "AttendanceGroup:create")
    @RequestMapping(value = "modal/not")
    public String showNotModal(Model model) {
        model.addAttribute("date", DateUtil.getToday());
        return viewName("/modal/notPunch");
    }
}
