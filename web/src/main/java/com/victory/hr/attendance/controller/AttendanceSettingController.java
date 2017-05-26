package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.OverTimeSetting;
import com.victory.hr.attendance.enums.CalculateType;
import com.victory.hr.attendance.service.OverTimeRecordService;
import com.victory.hr.attendance.service.OverTimeSettingService;
import com.victory.hr.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:49
 */
@Controller
@RequestMapping(value = "/attendance/setting")
public class AttendanceSettingController {

    @Autowired
    private OverTimeSettingService settingService;

    @RequiresPermissions(value = "AttendanceSetting:*")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        OverTimeSetting overTimeSetting = settingService.findOne(1);
        model.addAttribute("overTime", overTimeSetting);
        return "/attendance/setting/index";
    }

    @RequiresPermissions(value = "AttendanceSetting:update")
    @RequestMapping(value = "/overtime")
    public @ResponseBody
    JsonVo updateOverTime(int calculateType, int offsetTimeUp, int offsetTimeDown){
        OverTimeSetting setting = settingService.findOne(1);
        setting.setCalculateType(CalculateType.values()[calculateType]);
        setting.setOffsetTimeUp((long) (offsetTimeUp*60000));
        setting.setOffsetTimeDown((long) (offsetTimeDown*60000));
        settingService.update(setting);
        JsonVo jsonVo = new JsonVo().setStatus(true).setMsg("更新完成");
        return jsonVo;
    }
}
