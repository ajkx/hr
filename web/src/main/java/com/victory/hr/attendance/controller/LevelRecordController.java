package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceRecord;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.service.AttendanceRecordService;
import com.victory.hr.attendance.service.LevelRecordService;
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
@RequestMapping(value = "/attendance/levelrecord")
public class LevelRecordController extends BaseCURDController<LevelRecord,Integer>{

    private LevelRecordService getService() {
        return (LevelRecordService) baseService;
    }

    public LevelRecordController() {
        setResourceIdentity("LevelRecord");
    }

    @Override
    protected void setCommonData(Model model) {
    }
}
