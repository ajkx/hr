package com.victory.hr.service;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/6/12.
 * Time:8:44
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\test.xls";
        //    创建Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("考勤明细表");
        HSSFCellStyle style=workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        setHeader(sheet,style);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void setCell(HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("tata");
        row.createCell(1).setCellValue(false);
        row.createCell(2).setCellValue(new Date());
        row.createCell(3).setCellValue(123);

    }

    public static void setHeader(HSSFSheet sheet,HSSFCellStyle style) {


        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        int index = 0;
        int index1 = 4;
        HSSFCell cell = row.createCell(index);
        index++;
        cell.setCellValue("姓名");
        CellRangeAddress rangeAddress = new CellRangeAddress(0, 1, 0, 0);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        index++;
        cell.setCellValue("部门");
        rangeAddress = new CellRangeAddress(0, 1, 1, 1);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        index++;
        cell.setCellValue("工号");
        rangeAddress = new CellRangeAddress(0, 1, 2, 2);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        index++;
        cell.setCellValue("日期");
        rangeAddress = new CellRangeAddress(0, 1, 3, 3);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        index += 2;
        cell.setCellValue("上班一");
        rangeAddress = new CellRangeAddress(0, 0, 4, 5);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(index);
        index += 2;
        cell.setCellValue("下班一");
        rangeAddress = new CellRangeAddress(0, 0, 6, 7);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(index);
        index += 2;
        cell.setCellValue("上班二");
        rangeAddress = new CellRangeAddress(0, 0, 8, 9);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡结果");
        cell.setCellStyle(style);

        cell = row.createCell(index);
        index += 2;
        cell.setCellValue("下班二");
        rangeAddress = new CellRangeAddress(0, 0, 10, 11);
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row1.createCell(index1);
        index1++;
        cell.setCellValue("打卡时间");
        cell.setCellStyle(style);

        cell = row1.createCell(index1);
        index1++;
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


        cell = row.createCell(index);
        cell.setCellValue("迟到时间");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("早退时间");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("旷工时间");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("平时加班");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("周末加班");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("节日加班");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("请假时间(含出差)");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("规定出勤时间");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        cell = row.createCell(index);
        cell.setCellValue("实际出勤时间");
        rangeAddress = new CellRangeAddress(0, 1, index, index);
        index++;
        cell.setCellStyle(style);
        sheet.addMergedRegion(rangeAddress);

        sheet.setDefaultColumnWidth(12);
    }

}
