package com.victory.hr.attendance.service;


import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceSchedule;
import com.victory.hr.attendance.enums.AttendanceType;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.service.HrmResourceService;
import com.victory.hr.vo.JsonVo;
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
            map.put("date", detail.getDate());
            map.put("schedule", detail.getSchedule().getName());

            map.put("setting_first_up", StringUtils.nullString(detail.getSchedule().getFirst_time_up()));
            map.put("actual_first_up", StringUtils.nullString(detail.getFirst_time_up()));
            map.put("first_up_type", detail.getFirstUpType() == null ? "" : detail.getFirstUpType().getName());
            map.put("setting_first_down", StringUtils.nullString(detail.getSchedule().getFirst_time_down()));
            map.put("actual_first_down", StringUtils.nullString(detail.getFirst_time_down()));
            map.put("first_down_type", detail.getFirstDownType() == null ? "" : detail.getFirstDownType().getName());


            map.put("setting_second_up", StringUtils.nullString(detail.getSchedule().getSecond_time_up()));
            map.put("actual_second_up", StringUtils.nullString(detail.getSecond_time_up()));
            map.put("second_up_type", detail.getSecondUpType() == null ? "" : detail.getSecondUpType().getName());
            map.put("setting_second_down", StringUtils.nullString(detail.getSchedule().getSecond_time_down()));
            map.put("actual_second_down", StringUtils.nullString(detail.getSecond_time_down()));
            map.put("second_down_type", detail.getSecondDownType() == null ? "" : detail.getSecondDownType().getName());

            map.put("setting_third_up", StringUtils.nullString(detail.getSchedule().getThird_time_up()));
            map.put("actual_third_up", StringUtils.nullString(detail.getThird_time_up()));
            map.put("third_up_type", detail.getThirdUpType() == null ? "" : detail.getThirdUpType().getName());
            map.put("setting_third_down", StringUtils.nullString(detail.getSchedule().getThird_time_down()));
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
            map.put("level_time",levelTime);
            map.put("setting_time", StringUtils.nullString(detail.getShould_attendance_time()));
            map.put("actual_time", StringUtils.nullString(detail.getActual_attendance_time()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public PageInfo findAllCollect(HttpServletRequest request) {
        int pageNo = Integer.parseInt(request.getParameter("cPage"));
        int pageSize = Integer.parseInt(request.getParameter("pSize"));
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
            HrmResource resource = (HrmResource) obj[16];
            map.put("name",resource.getName());
            map.put("department",resource.getDepartment().getName());
            map.put("workCode",resource.getWorkCode());
            map.put("should_attendance",StringUtils.nullLong(obj[0]));
            map.put("actual_attendance",StringUtils.nullDouble(obj[1]));
            map.put("should_attendance_time",StringUtils.nullLong(obj[2]));
            map.put("actual_attendance_time",StringUtils.nullLong(obj[3]));
            map.put("late_count",StringUtils.nullLong(obj[4]));
            map.put("late_time",StringUtils.nullLong(obj[5]));
            map.put("early_count",StringUtils.nullLong(obj[6]));
            map.put("early_time",StringUtils.nullLong(obj[7]));
            map.put("absenteeism_count",StringUtils.nullLong(obj[8]));
            map.put("absenteeism_time",StringUtils.nullLong(obj[9]));
            map.put("ot_normal",StringUtils.nullLong(obj[10]));
            map.put("ot_weekend",StringUtils.nullLong(obj[11]));
            map.put("ot_festival",StringUtils.nullLong(obj[12]));
            map.put("leave_personal",StringUtils.nullLong(obj[13]));
            map.put("leave_rest",StringUtils.nullLong(obj[14]));
            map.put("leave_business",StringUtils.nullLong(obj[15]));
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

    public JsonVo updateDetail(int id, int type) {
        AttendanceDetail detail = findOne(id);
        JsonVo jsonVo = new JsonVo();
        if (detail == null) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("该操作失效！");
        }else{
            AttendanceSchedule schedule = detail.getSchedule();
            switch (type) {
                case 1:
                    detail.setFirst_time_up(schedule.getFirst_time_up());
                    detail.setFirstUpType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getFirst_time_up());
                    break;
                case 2:
                    detail.setFirst_time_down(schedule.getFirst_time_down());
                    detail.setFirstDownType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getFirst_time_down());
                    break;
                case 3:
                    detail.setSecond_time_up(schedule.getSecond_time_up());
                    detail.setSecondUpType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getSecond_time_up());
                    break;
                case 4:
                    detail.setFirst_time_down(schedule.getSecond_time_down());
                    detail.setSecondDownType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getSecond_time_down());
                    break;
                case 5:
                    detail.setThird_time_up(schedule.getThird_time_up());
                    detail.setThirdUpType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getThird_time_up());
                    break;
                case 6:
                    detail.setThird_time_down(schedule.getThird_time_down());
                    detail.setThirdDownType(AttendanceType.normal);
                    jsonVo.put("time",schedule.getThird_time_down());
                    break;
            }
            calculate.calculateTime(detail);
            update(detail);
            jsonVo.setStatus(true).setMsg("修改成功");
        }
        return jsonVo;
    }
}
