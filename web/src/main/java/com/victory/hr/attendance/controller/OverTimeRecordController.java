package com.victory.hr.attendance.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.attendance.enums.OverTimeType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.attendance.service.LevelRecordService;
import com.victory.hr.attendance.service.OverTimeRecordService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Pageable;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.service.HrmResourceService;
import com.victory.hr.vo.JsonVo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/attendance/overtimerecord")
public class OverTimeRecordController extends BaseCURDController<OverTimeRecord,Integer>{

    @Autowired
    private HrmResourceService resourceService;

    private OverTimeRecordService getService() {
        return (OverTimeRecordService) baseService;
    }

    public OverTimeRecordController() {
        setResourceIdentity("OverTimeRecord");
    }

    @Override
    protected void setCommonData(Model model,OverTimeRecord record) {
    }

    @Override
    protected void setIndexData(Model model) {
        String beginStr = DateUtils.getMonthFristDay().toString();
        String endStr = DateUtils.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
    }

    /**
     * 返回批量创建的页面
     * @param model
     * @return
     */
    @RequiresPermissions(value = "OverTimeRecord:create")
    @RequestMapping(value = "/create/batch", method = RequestMethod.GET)
    public String showBacthCreate(Model model) {
        setIndexData(model);
        return viewName("batchEditForm");
    }

    /**
     * 执行批量创建操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "OverTimeRecord:create")
    @RequestMapping(value = "/create/batch", method = RequestMethod.POST)
    public @ResponseBody JsonVo batchCreate(HttpServletRequest request) {
        String beginDateStr = StringUtils.nullString(request.getParameter("date"));
        String endDateStr = StringUtils.nullString(request.getParameter("endDate"));
        JsonVo jsonVo = new JsonVo();
        if("".equals(beginDateStr) || "".equals(endDateStr) || endDateStr.compareTo(beginDateStr) <= 0){
            jsonVo.setStatus(false).setMsg("时间不合法！");
            return jsonVo;
        }
        String resourceStr = StringUtils.nullString(request.getParameter("resources"));
        String reason = StringUtils.nullString(request.getParameter("reason"));
        String type = StringUtils.nullString(request.getParameter("type"));

        Date beginDate = DateUtils.parseUtilDate(beginDateStr);
        Date endDate = DateUtils.parseUtilDate(endDateStr);

        String[] arrays = resourceStr.split(",");
        for (String temp : arrays) {
            if("".equals(temp)) continue;
            OverTimeRecord record = new OverTimeRecord();
            HrmResource resource = resourceService.findOne(Integer.parseInt(temp));
            if(resource == null) continue;
            record.setResource(resource);
            record.setDate(beginDate);
            record.setEndDate(endDate);
            record.setReason(reason);
            record.setStatus(Status.normal);
            record.setType(OverTimeType.valueOf(type));
            record.setLink(false);
            record.setTotalTime(DateUtils.getTimeInterval(beginDate,endDate));
            getService().save(record);
        }
        jsonVo.setStatus(true).setMsg("批量创建成功");
        return jsonVo;
    }
    /**
     * 修改异常
     * @param id
     * @return
     */
    @RequiresPermissions(value = "OverTimeRecord:update")
    @RequestMapping(value = "update/{id}")
    public @ResponseBody
    JsonVo updateDetail(@PathVariable int id){
        return getService().updateRecord(id);
    }

    @RequestMapping(value = "/excel")
    public void getRecordExcel(HttpServletRequest request, HttpServletResponse response) {
        try{
            String status = request.getParameter("status");
            String beginDate = request.getParameter("beginDate");
            String endDate = request.getParameter("endDate");
            String fileName = "";
            if ("abnormal".equals(status)) {
                fileName = "加班异常表";
            }else{
                fileName = "加班表";
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((beginDate + "至" + endDate+fileName).getBytes(),"iso-8859-1")+".xls");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream os = response.getOutputStream();

            Searchable searchable = getSearchable(request);
            Pageable pageable = searchable.getPageable();
            pageable.setPaging(false);

            PageInfo pageInfo = getService().findAll(searchable);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(fileName);
            sheet.setDefaultColumnWidth(12);
            HSSFCellStyle style=workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            setHeader(sheet,style);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setHeader(HSSFSheet sheet, HSSFCellStyle style) {

    }
}
