package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.service.AttendanceCalculate;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.vo.JsonVo;
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

    @Autowired
    private AttendanceCalculate calculate;

    private AttendanceDetailService getService() {
        return (AttendanceDetailService) baseService;
    }

    public AttendanceDetailController() {
        setResourceIdentity("AttendanceDetail");
    }

    @Override
    protected void setCommonData(Model model,AttendanceDetail detail) {
    }

    @Override
    protected void setIndexData(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
    }

    /**
     * 考勤汇总首页
     * @param model
     * @return
     */
    @RequiresPermissions(value = "AttendanceCollect:view")
    @RequestMapping(value = "/collect")
    public String collect(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString().substring(0,7);
        model.addAttribute("beginDate", beginStr);
        return "attendance/detail/collect";
    }

    /**
     * 考勤汇总的列表
     * @param request
     * @return
     */
    @RequiresPermissions(value = "AttendanceCollect:view")
    @RequestMapping(value = "/collect/list")
    public @ResponseBody
    PageInfo collect(HttpServletRequest request) {
        PageInfo pageInfo = getService().findAllCollect(request);
        return pageInfo;
    }

    /**
     * 修改异常出勤的
     * @param id
     * @param type
     * @return
     */
    @RequiresPermissions(value = "AttendanceDetail:update")
    @RequestMapping(value = "update/{id}/{type}")
    public @ResponseBody
    JsonVo updateDetail(@PathVariable int id, @PathVariable int type){
        return getService().updateDetail(id, type);
    }

    @RequestMapping(value = "/test")
    public void test() {
        calculate.calculateAll();

    }
}
