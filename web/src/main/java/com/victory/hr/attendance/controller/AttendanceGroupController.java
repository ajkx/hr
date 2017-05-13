package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.attendance.service.AttendanceGroupService;
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
@RequestMapping(value = "/attendance/group")
public class AttendanceGroupController extends BaseCURDController<AttendanceGroup,Integer>{

    private AttendanceGroupService getService() {
        return (AttendanceGroupService) baseService;
    }

    public AttendanceGroupController() {
        setResourceIdentity("AttendanceGroup");
    }

    @Override
    protected void setCommonData(Model model) {
    }
}
