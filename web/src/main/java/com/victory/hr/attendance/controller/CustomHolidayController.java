package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.CustomHoliday;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.service.CustomHolidayService;
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
@RequestMapping(value = "/attendance/customholiday")
public class CustomHolidayController extends BaseCURDController<CustomHoliday,Integer>{

    private CustomHolidayService getService() {
        return (CustomHolidayService) baseService;
    }

    public CustomHolidayController() {
        setResourceIdentity("LevelRecord");
    }

    @Override
    protected void setCommonData(Model model,CustomHoliday holiday) {

    }

    @Override
    protected void setIndexData(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
    }
}
