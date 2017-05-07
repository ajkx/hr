package com.victory.hr.util;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2016/12/14.
 * Time:16:17
 */
public class DateUtil {

    /**
     * 获取sql.date格式的今天日期
     * @return
     */
    public static Date getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    /**
     * 获取sql.date格式的昨天日期
     * @return
     */
    public static Date getYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static Date getYesterday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    public static Date getMonthFristDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getMonthLastDay(Date date){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return new Date(calendar.getTimeInMillis());
    }
    /**
     * 获取sql.date格式的前天日期
     * @return
     */
    public static Date getBeforeYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-2);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static Date getNextDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }
    public static Long getOneDayTime(){
        return Long.valueOf(86400000);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 返回两个日期之间的日期集合，包括开始日期和结束日期
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> getDateList(Date begin,Date end){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(begin);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        List<Date> dateList = new ArrayList<>();
        while(cal1.compareTo(cal2) <= 0){
            dateList.add(new Date(cal1.getTimeInMillis()));
            cal1.add(Calendar.DATE,1);
        }
        return dateList;
    }

    public static List<Date> getDateList(long begin,long end){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(begin);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(end);
        List<Date> dateList = new ArrayList<>();
        while(cal1.compareTo(cal2) <= 0){
            dateList.add(new Date(cal1.getTimeInMillis()));
            cal1.add(Calendar.DATE,1);
        }
        return dateList;
    }


    public static Date parseDateByDay(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(str));
            calendar.set(Calendar.HOUR,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
            calendar.set(Calendar.MILLISECOND,999);
            date = new Date(calendar.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static java.util.Date parseUtilDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        java.util.Date date = null;
        try{
            date = sdf.parse(str);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    public static java.util.Date parseUtilDate1(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date = null;
        try{
            date = sdf.parse(str);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
    public static Date parseDateByMonth(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = new Date(sdf.parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long parseSqlDateAndTime(String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date1 = null;
        try {
            date1 = sdf.parse(date + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1.getTime();
    }

    public static java.util.Date parseStrToDate(Date beginDate,long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return null;
    }
    public static Time parseTime(String str){
        SimpleDateFormat sdf = new SimpleDateFormat();
        return null;
    }

    public static long getTimeInterval(Time beginTime, Time endTime) {
        System.out.println(endTime.getTime());
        System.out.println(beginTime.getTime());
        long time = endTime.getTime() - beginTime.getTime();
        return time;
    }

    public static long getTimeInterval(java.util.Date beginDate, java.util.Date endDate) {
        long time = endDate.getTime() - beginDate.getTime();
        return time;
    }

    public static java.util.Date getLastTimeInDay(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date lastDate = null;
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        lastDate = new Date(calendar.getTimeInMillis());
        return lastDate;
    }

    public static Long clearDate(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR,1970);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DATE,1);
        return calendar.getTimeInMillis();
    }

    public static Long clearDate(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.YEAR,1970);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DATE,1);
        return calendar.getTimeInMillis();
    }
}
