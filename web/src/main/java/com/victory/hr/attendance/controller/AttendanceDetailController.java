package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/detail")
public class AttendanceDetailController extends BaseCURDController<AttendanceDetail,Integer>{

    private AttendanceDetailService getService() {
        return (AttendanceDetailService) baseService;
    }

    public AttendanceDetailController() {
        setResourceIdentity("AttendanceDetail");
    }

    @Override
    protected void setCommonData(Model model) {
    }
}
