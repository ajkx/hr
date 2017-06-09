package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.entity.RepairRecord;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.attendance.service.AttendanceCalculate;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.attendance.service.LevelRecordService;
import com.victory.hr.attendance.service.RepairRecordService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/repairrecord")
public class RepairRecordController extends BaseCURDController<RepairRecord,Integer>{

    @Autowired
    private AttendanceDetailService detailService;

    @Autowired
    private AttendanceCalculate attendanceCalculate;

    private RepairRecordService getService() {
        return (RepairRecordService) baseService;
    }

    public RepairRecordController() {
        setResourceIdentity("RepairRecord");
    }

    @Override
    protected void setCommonData(Model model,RepairRecord record) {

    }

    @Override
    protected void setIndexData(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
    }

    /**
     * 修改异常出勤的
     * @param id
     * @param type
     * @return
     */
    @RequiresPermissions(value = "RepairRecord:create")
    @RequestMapping(value = "updatedetail/{id}/{type}")
    public @ResponseBody
    JsonVo updateDetail(@PathVariable int id, @PathVariable int type,String reason){
        AttendanceDetail detail = detailService.findOne(id);
        JsonVo jsonVo = new JsonVo();
        if (detail == null) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("该操作失效！");
        }else{
            RepairRecord repairRecord = new RepairRecord();
            repairRecord.setStatus(Status.calculate);
            repairRecord.setDetail(detail);
            repairRecord.setDate(detail.getDate());
            repairRecord.setResource(detail.getResource());
            repairRecord.setPosition(type + "");
            repairRecord.setReason(reason);
            //先持久化记录
            getService().save(repairRecord);

            boolean result = getService().updateDetail(repairRecord);
            if(result){
                attendanceCalculate.calculateTime(repairRecord.getDetail());
                detailService.update(repairRecord.getDetail());
            }
            jsonVo.setStatus(true);
            jsonVo.setMsg("修改成功!");
        }
        return jsonVo;
    }
}
