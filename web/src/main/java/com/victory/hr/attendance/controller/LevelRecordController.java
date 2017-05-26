package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceRecord;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.service.AttendanceRecordService;
import com.victory.hr.attendance.service.LevelRecordService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.utils.DateUtils;
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
    protected void setCommonData(Model model,LevelRecord record) {

    }

    @Override
    protected void setIndexData(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
    }
}
