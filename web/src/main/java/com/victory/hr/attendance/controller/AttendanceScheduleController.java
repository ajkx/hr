package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.attendance.service.AttendanceGroupService;
import com.victory.hr.attendance.service.AttendanceScheduleService;
import com.victory.hr.base.BaseCURDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    protected void setCommonData(Model model) {
    }
}
