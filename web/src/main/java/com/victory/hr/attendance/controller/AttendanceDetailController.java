package com.victory.hr.attendance.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.service.AttendanceCalculate;
import com.victory.hr.attendance.service.AttendanceDetailService;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Pageable;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.vo.JsonVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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



    @RequestMapping(value = "/test")
    public void test() {
        calculate.calculateAll();

    }

    /**
     * 导出明细（异常）报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/excel")
    public void getDetailExcel(HttpServletRequest request, HttpServletResponse response) {
        try{
            String status = request.getParameter("status");
            String beginDate = request.getParameter("beginDate");
            String endDate = request.getParameter("endDate");
            String fileName = "";
            if ("abnormal".equals(status)) {
                fileName = "考勤异常表";
            }else{
                fileName = "考勤明细表";
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

            List<Map<String, String>> list = pageInfo.getData();
            int rowIndex = 2;
            for (Map<String, String> map : list) {
                HSSFRow row = sheet.createRow(rowIndex);
                int cellIndex = 0;
                HSSFCell cell = row.createCell(cellIndex);
                cell.setCellValue( map.get("name"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("department"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("workCode"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("date"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("schedule"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_first_up"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("first_up_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_first_down"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("first_down_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_second_up"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("second_up_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_second_down"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("second_down_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_third_up"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("third_up_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_third_down"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("third_down_type"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("lateTime"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("earlyTime"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("absenteeismTime"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_normal"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_weekend"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_festival"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("level_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("setting_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_time"));
                cell.setCellStyle(style);
                rowIndex++;
            }
            workbook.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHeader(HSSFSheet sheet,HSSFCellStyle style) {

        int cellIndex = 0;
        int cellIndex2 = 5;

        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);

        HSSFCell cell = row.createCell(cellIndex);
        cell.setCellValue("姓名");
        CellRangeAddress rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("部门");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("工号");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("日期");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("班次");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("上班一");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+1);
        cellIndex += 2;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex);
        cell.setCellValue("下班一");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+1);
        cellIndex += 2;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex);
        cell.setCellValue("上班二");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+1);
        cellIndex += 2;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex);
        cell.setCellValue("下班二");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+1);
        cellIndex += 2;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        //第三个上下班 百利没有 可以注释掉 变为加班的上下班
//        cell = row.createCell(index);
//        index += 2;
//        cell.setCellValue("上班三");
//        rangeAddress = new CellRangeAddress(0, 0, 12, 13);
//        cell.setCellStyle(style);
//        sheet.addMergedRegion(rangeAddress);
//
//        cell = row1.createCell(index1);
//        index1++;
//        cell.setCellValue("打卡时间");
//        cell.setCellStyle(style);
//
//        cell = row1.createCell(index1);
//        index1++;
//        cell.setCellValue("打卡结果");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(index);
//        index += 2;
//        cell.setCellValue("下班三");
//        rangeAddress = new CellRangeAddress(0, 0, 14, 15);
//        cell.setCellStyle(style);
//        sheet.addMergedRegion(rangeAddress);
//
//        cell = row1.createCell(index1);
//        index1++;
//        cell.setCellValue("打卡时间");
//        cell.setCellStyle(style);
//
//        cell = row1.createCell(index1);
//        index1++;
//        cell.setCellValue("打卡结果");
//        cell.setCellStyle(style);


        cell = row.createCell(cellIndex);
        cell.setCellValue("迟到时间");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("早退时间");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("旷工时间");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("平时加班");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("周末加班");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("节日加班");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("请假时间(含出差)");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("规定出勤时间");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("实际出勤时间");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);
    }

    /**
     * 导出汇总报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/collectexcel")
    public void getCollectExcel(HttpServletRequest request, HttpServletResponse response) {
        try{
            String beginDate = request.getParameter("beginDate");
            String fileName = "考勤汇总表";

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((beginDate+fileName).getBytes(),"iso-8859-1")+".xls");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream os = response.getOutputStream();

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("考勤汇总表");

            HSSFCellStyle style=workbook.createCellStyle();
//            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            setCollectHeader(sheet,style);

            PageInfo pageInfo = getService().findAllCollect(request);
            List<Map<String, Double>> list = pageInfo.getData();
            int rowIndex = 2;
            for (Map<String, Double> map : list) {
                HSSFRow row = sheet.createRow(rowIndex);
                int cellIndex = 0;
                HSSFCell cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("name")+"");
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("department")+"");
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("workCode")+"");
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("should_attendance"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_attendance"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("should_attendance_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("actual_attendance_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("late_count"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("late_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("early_count"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("early_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("absenteeism_count"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("absenteeism_time"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_normal"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_weekend"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("ot_festival"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("leave_personal"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("leave_rest"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("leave_business"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue(map.get("leave_sick"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue((Double) map.get("leave_injury"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue((Double) map.get("leave_delivery"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue((Double) map.get("leave_married"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue((Double) map.get("leave_funeral"));
                cell.setCellStyle(style);
                cellIndex++;

                cell = row.createCell(cellIndex);
                cell.setCellValue((Double) map.get("leave_annual"));
                cell.setCellStyle(style);
                cellIndex++;

                rowIndex++;
            }
            sheet.setColumnWidth(3,12 * 256);
            sheet.setColumnWidth(4,12 * 256);
            sheet.setColumnWidth(5,12 * 256);
            sheet.setColumnWidth(6,12 * 256);
            workbook.write(os);
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCollectHeader(HSSFSheet sheet, HSSFCellStyle style) {
        int cellIndex = 0;
        int cellIndex2 = 7;

        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);

        HSSFCell cell = row.createCell(cellIndex);
        cell.setCellValue("姓名");
        CellRangeAddress rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("部门");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("工号");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("规定出勤天数");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("实际出勤天数");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("规定出勤时数");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("实际出勤时数");
        rangeAddress = new CellRangeAddress(0, 1, cellIndex, cellIndex);
        cellIndex++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(cellIndex);
        cell.setCellValue("异常");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+5);
        cellIndex += 6;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("迟到次数");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("迟到时长");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("早退次数");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("早退时长");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("旷工次数");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("旷工时长");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex);
        cell.setCellValue("加班");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+2);
        cellIndex += 3;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("平时");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("周末");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("节日");
        cell.setCellStyle(style);

        cell = row.createCell(cellIndex);
        cell.setCellValue("请假");
        rangeAddress = new CellRangeAddress(0, 0, cellIndex, cellIndex+8);
        cellIndex += 9;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("事假");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("调休");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("出差");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("工伤");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("产假");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("婚假");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("丧假");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("年假");
        cell.setCellStyle(style);

        cell = row1.createCell(cellIndex2);
        cellIndex2++;
        cell.setCellValue("病假");
        cell.setCellStyle(style);

    }
}
