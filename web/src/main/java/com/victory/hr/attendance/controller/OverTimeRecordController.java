package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.attendance.service.LevelRecordService;
import com.victory.hr.attendance.service.OverTimeRecordService;
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
@RequestMapping(value = "/attendance/overtimerecord")
public class OverTimeRecordController extends BaseCURDController<OverTimeRecord,Integer>{

    private OverTimeRecordService getService() {
        return (OverTimeRecordService) baseService;
    }

    public OverTimeRecordController() {
        setResourceIdentity("OverTimeRecord");
    }

    @Override
    protected void setCommonData(Model model) {
    }
}
