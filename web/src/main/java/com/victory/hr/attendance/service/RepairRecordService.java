package com.victory.hr.attendance.service;

import com.victory.hr.attendance.dao.RepairRecordDao;
import com.victory.hr.attendance.entity.*;
import com.victory.hr.attendance.enums.AttendanceType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/28.
 * Time:10:47
 */
@Service
public class RepairRecordService extends BaseService<RepairRecord,Integer> {

    @Autowired
    private AttendanceCalculate calculate;

    @Autowired
    private OverTimeRecordService overTimeRecordService;

    @Autowired
    private AttendanceDetailService detailService;

    private RepairRecordDao getDao() {
        return (RepairRecordDao) baseDao;
    }
    /**
     * 异常修补
     * @param repairRecord
     */
    public boolean updateDetail(RepairRecord repairRecord) {
        AttendanceDetail detail = repairRecord.getDetail();
        if (detail == null) {
            HrmResource resource = repairRecord.getResource();
            java.sql.Date date = repairRecord.getDate();
            List<AttendanceDetail> details = detailService.findByHrmResourceAndDate(resource, date);
            //未查询到明细数据则退出
            if (details.size() < 1) {
                return false;
            }else{
                detail = details.get(0);
            }
        }
        Set<OverTimeRecord> overTimeRecordSet = detail.getOverTimeRecords();
        String position = repairRecord.getPosition();
        String[] positions = position.split(",");
        AttendanceSchedule schedule = detail.getSchedule();

        boolean isUpdate = false;
        for(int i = 0; i < positions.length; i++) {
            if (!"".equals(positions[i])) {
                isUpdate = true;
                switch (positions[i]) {
                    case "1":
                        detail.setFirst_time_up(schedule.getFirst_time_up());
                        detail.setFirstUpType(AttendanceType.normal);
                        break;
                    case "2":
                        detail.setFirst_time_down(schedule.getFirst_time_down());
                        detail.setFirstDownType(AttendanceType.normal);
                        break;
                    case "3":
                        detail.setSecond_time_up(schedule.getSecond_time_up());
                        detail.setSecondUpType(AttendanceType.normal);
                        break;
                    case "4":
                        detail.setFirst_time_down(schedule.getSecond_time_down());
                        detail.setSecondDownType(AttendanceType.normal);
                        break;
                    case "5":
                        detail.setThird_time_up(schedule.getThird_time_up());
                        detail.setThirdUpType(AttendanceType.normal);
                        break;
                    case "6":
                        detail.setThird_time_down(schedule.getThird_time_down());
                        detail.setThirdDownType(AttendanceType.normal);
                        break;
                    case "7":
                        for (OverTimeRecord overTimeRecord : overTimeRecordSet) {
                            if (overTimeRecord.getStatus() == Status.abnormal) {
                                overTimeRecord.setActualBeginDate(overTimeRecord.getDate());
                                overTimeRecord.setActualEndDate(overTimeRecord.getEndDate());
                                overTimeRecord.setActualTotalTime(overTimeRecord.getTotalTime());
                                overTimeRecord.setStatus(Status.finish);
                                overTimeRecord.setRemark("异常修改为正常");
                                calculate.updateDetailByOverTimeRecord(detail,overTimeRecord);
                                calculate.calculateTime(detail);
                                overTimeRecordService.update(overTimeRecord);
                            }
                        }
                }
            }
        }
        repairRecord.setDetail(detail);
        repairRecord.setStatus(Status.finish);
        update(repairRecord);
        //防止position为空，进行多余的运算
        return isUpdate;

    }

    @Override
    public RepairRecord save(RepairRecord entity) {
        entity.setStatus(Status.calculate);
        return super.save(entity);
    }

    @Override
    public void update(RepairRecord entity) {
        entity.setStatus(Status.calculate);
        super.update(entity);
    }
    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = baseDao.findAll(searchable);
        List<RepairRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (RepairRecord record : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            String str = StringUtils.nullString(record.getPosition());
            String position = "";
            if(!"".equals(str)){
                String[] positions = str.split(",");

                for(int i = 0; i < positions.length;i++) {
                    if(!"".equals(positions[i])){
                        switch (positions[i]){
                            case "1":
                                position += "第一次上班";
                                break;
                            case "2":
                                position += "第一次下班";
                                break;
                            case "3":
                                position += "第二次上班";
                                break;
                            case "4":
                                position += "第二次下班";
                                break;
                            case "5":
                                position += "第三次上班";
                                break;
                            case "6":
                                position += "第三次下班";
                                break;
                            case "7":
                                position += "加班上班";
                                break;
                            case "8":
                                position += "加班下班";
                                break;

                        }
                        position += "</br>";
                    }
                }
            }
            map.put("position", position);
            map.put("date", record.getDate());
            map.put("reason", StringUtils.nullString(record.getReason()));
            map.put("status", record.getStatus().getName());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public List<RepairRecord> findByStatus(Status status) {
        return getDao().findByStatus(status);
    }

    public List<RepairRecord> findByHrmResourceAndDate(HrmResource resource, Date date) {
        return getDao().findByHrmResourceAndDate(resource, date);
    }
}
