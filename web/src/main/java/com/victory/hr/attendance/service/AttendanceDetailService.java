package com.victory.hr.attendance.service;


import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.service.HrmResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:9:51
 */
@Service
public class AttendanceDetailService extends BaseService<AttendanceDetail,Integer> {

    @Autowired
    private AttendanceCalculate calculate;

    @Autowired
    private AttendanceScheduleService scheduleService;

    @Autowired
    private HrmResourceService resourceService;

    private AttendanceDetailDao getDao() {
        return (AttendanceDetailDao) baseDao;
    }
    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<AttendanceDetail> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (AttendanceDetail detail : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", detail.getId());
            map.put("name", detail.getResource().getName());
            map.put("department", detail.getResource().getDepartment().getName());
            map.put("workCode", detail.getResource().getWorkCode());
            map.put("date", detail.getDate().toString());
            AttendanceSchedule schedule = detail.getSchedule();
            map.put("schedule", schedule == null ? "不在考勤组" : schedule.getName()+" "+scheduleService.getScheduleTime(schedule));

            map.put("setting_first_up", schedule == null ? "" : StringUtils.nullString(schedule.getFirst_time_up()));
            map.put("actual_first_up", StringUtils.nullString(detail.getFirst_time_up()));
            map.put("first_up_type", detail.getFirstUpType() == null ? "" : detail.getFirstUpType().getName());
            map.put("setting_first_down", schedule == null ? "" : StringUtils.nullString(schedule.getFirst_time_down()));
            map.put("actual_first_down", StringUtils.nullString(detail.getFirst_time_down()));
            map.put("first_down_type", detail.getFirstDownType() == null ? "" : detail.getFirstDownType().getName());


            map.put("setting_second_up", schedule == null ? "" : StringUtils.nullString(schedule.getSecond_time_up()));
            map.put("actual_second_up", StringUtils.nullString(detail.getSecond_time_up()));
            map.put("second_up_type", detail.getSecondUpType() == null ? "" : detail.getSecondUpType().getName());
            map.put("setting_second_down", schedule == null ? "" : StringUtils.nullString(schedule.getSecond_time_down()));
            map.put("actual_second_down", StringUtils.nullString(detail.getSecond_time_down()));
            map.put("second_down_type", detail.getSecondDownType() == null ? "" : detail.getSecondDownType().getName());

            map.put("setting_third_up", schedule == null ? "" : StringUtils.nullString(schedule.getThird_time_up()));
            map.put("actual_third_up", StringUtils.nullString(detail.getThird_time_up()));
            map.put("third_up_type", detail.getThirdUpType() == null ? "" : detail.getThirdUpType().getName());
            map.put("setting_third_down", schedule == null ? "" : StringUtils.nullString(schedule.getThird_time_down()));
            map.put("actual_third_down", StringUtils.nullString(detail.getThird_time_down()));
            map.put("third_down_type", detail.getThirdDownType() == null ? "" : detail.getThirdDownType().getName());

            map.put("attendanceType", detail.getStatus().getName());
            map.put("lateTime", StringUtils.nullString(detail.getLateTime()));
            map.put("earlyTime", StringUtils.nullString(detail.getEarlyTime()));
            map.put("absenteeismTime", StringUtils.nullString(detail.getAbsenteeismTime()));
            map.put("ot_normal", StringUtils.nullString(detail.getOvertime_normal()));
            map.put("ot_weekend", StringUtils.nullString(detail.getOvertime_weekend()));
            map.put("ot_festival", StringUtils.nullString(detail.getOvertime_festival()));
            //请假时间合计
            long levelTime = StringUtils.nullLong(detail.getLeave_annual())
                    + StringUtils.nullLong(detail.getLeave_sick())
                    + StringUtils.nullLong(detail.getLeave_delivery())
                    + StringUtils.nullLong(detail.getLeave_funeral())
                    + StringUtils.nullLong(detail.getLeave_married())
                    + StringUtils.nullLong(detail.getLeave_business())
                    + StringUtils.nullLong(detail.getLeave_injury())
                    + StringUtils.nullLong(detail.getLeave_personal())
                    + StringUtils.nullLong(detail.getLeave_rest());
            map.put("level_time",levelTime+"");
            map.put("setting_time", StringUtils.nullString(detail.getShould_attendance_time()));
            map.put("actual_time", StringUtils.nullString(detail.getActual_attendance_time()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public PageInfo findAllCollect(HttpServletRequest request) {
        String page = StringUtils.nullString(request.getParameter("cPage"));
        String size = StringUtils.nullString(request.getParameter("pSize"));
        int pageNo = page.equals("") ? 0 :Integer.parseInt(page);
        int pageSize = page.equals("") ? 0 :Integer.parseInt(size);
        String beginStr = StringUtils.nullString(request.getParameter("beginDate"));
        String endStr = StringUtils.nullString(request.getParameter("endDate"));
        String resources = StringUtils.nullString(request.getParameter("resources"));
        Date beginDate = null;
        Date endDate = DateUtils.getMonthLastDay(null);
        Set<HrmResource> resourceSet = null;
        if ("".equals(beginStr)) {
            beginDate = DateUtils.getMonthFristDay();
        }else{
            beginDate = DateUtils.parseDateByMonth(beginStr);
            endDate = DateUtils.getMonthLastDay(beginDate);
        }
        if(!"".equals(resources)){
            resourceSet = resourceService.splitForHrmResource(resources);
        }
        return convertDateByCollect(getDao().findAllCollect(beginDate, endDate, resourceSet, pageNo, pageSize));
    }

    private PageInfo convertDateByCollect(PageInfo info){
        List<Object[]> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Object[] obj : list) {
            Map<String, Object> map = new HashMap<>();
            HrmResource resource = (HrmResource) obj[22];
            map.put("name",resource.getName());
            map.put("department",resource.getDepartment().getName());
            map.put("workCode",resource.getWorkCode());
            map.put("should_attendance",StringUtils.nullDouble(obj[0]));
            map.put("actual_attendance",StringUtils.nullDouble(obj[1]));
            map.put("should_attendance_time",StringUtils.nullDouble(obj[2]));
            map.put("actual_attendance_time",StringUtils.nullDouble(obj[3]));
            map.put("late_count",StringUtils.nullDouble(obj[4]));
            map.put("late_time",StringUtils.nullDouble(obj[5]));
            map.put("early_count",StringUtils.nullDouble(obj[6]));
            map.put("early_time",StringUtils.nullDouble(obj[7]));
            map.put("absenteeism_count",StringUtils.nullDouble(obj[8]));
            map.put("absenteeism_time",StringUtils.nullDouble(obj[9]));
            map.put("ot_normal",StringUtils.nullDouble(obj[10]));
            map.put("ot_weekend",StringUtils.nullDouble(obj[11]));
            map.put("ot_festival",StringUtils.nullDouble(obj[12]));
            map.put("leave_personal",StringUtils.nullDouble(obj[13]));
            map.put("leave_rest",StringUtils.nullDouble(obj[14]));
            map.put("leave_business",StringUtils.nullDouble(obj[15]));
            map.put("leave_sick",StringUtils.nullDouble(obj[16]));
            map.put("leave_injury",StringUtils.nullDouble(obj[17]));
            map.put("leave_delivery",StringUtils.nullDouble(obj[18]));
            map.put("leave_married",StringUtils.nullDouble(obj[19]));
            map.put("leave_funeral",StringUtils.nullDouble(obj[20]));
            map.put("leave_annual",StringUtils.nullDouble(obj[21]));

            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public List<AttendanceDetail> findAcrossDayByDate(Date date){
        return getDao().findAcrossDayByDate(date);
    }

    public List<AttendanceDetail> findByHrmResourceAndDate(HrmResource resource, Date date) {
        return getDao().findByHrmResourceAndDate(resource, date);
    }


}
