package com.victory.hr.attendance.controller;

import com.sun.prism.impl.Disposer;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceRecord;
import com.victory.hr.attendance.enums.RecordType;
import com.victory.hr.attendance.enums.Reviews;
import com.victory.hr.attendance.enums.TestType;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.attendance.service.AttendanceRecordService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/record")
public class AttendanceRecordController extends BaseCURDController<AttendanceRecord,Integer>{

    private AttendanceRecordService getService() {
        return (AttendanceRecordService) baseService;
    }

    public AttendanceRecordController() {
        setResourceIdentity("ManualRecord");
    }

    @Override
    protected void setCommonData(Model model) {
    }

    //考勤机记录主页
    @RequiresPermissions(value = "MachineRecord:view")
    @RequestMapping(value = "/machine")
    public String machineIndex(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        return viewName("machineIndex");
    }

    //签卡记录主页
    @RequiresPermissions(value = "ManualRecord:view")
    @RequestMapping(value = "/manual")
    public String manualIndex(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        return viewName("manualIndex");
    }

    @RequestMapping(value = "/test")
    public void test(@RequestBody Reviews reviews) {
        System.out.println("");
        System.out.println(reviews.getReos().getText());
        System.out.println("aaa");
    }
}
