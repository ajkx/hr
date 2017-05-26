package com.victory.hr.attendance.service;


import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class OverTimeRecordService extends BaseService<OverTimeRecord,Integer> {

    @Autowired
    private AttendanceCalculate calculate;

    @Autowired
    private AttendanceDetailService detailService;

    @Override
    public OverTimeRecord save(OverTimeRecord entity) {
        entity.setStatus(Status.calculate);
        //目前判断连班是在考勤计算的时候，也可以在创建或更新时进行连班判断，但会存在先加班申请，再修改班次
        entity.setLink(false);
        entity.setTotalTime(DateUtils.getTimeInterval(entity.getDate(),entity.getEndDate()));
        return super.save(entity);
    }

    @Override
    public void update(OverTimeRecord entity) {
        entity.setStatus(Status.calculate);
        entity.setLink(false);
        entity.setTotalTime(DateUtils.getTimeInterval(entity.getDate(),entity.getEndDate()));
        super.update(entity);
    }

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<OverTimeRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (OverTimeRecord record : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("type", record.getType().getName());
            map.put("beginDate", record.getDate());
            map.put("actualUp", record.getActualBeginDate());
            map.put("endDate", record.getEndDate());
            map.put("actualDown", record.getActualEndDate());
            map.put("sum", record.getTotalTime());
            map.put("actualSum", record.getActualTotalTime());
            map.put("reason", record.getReason());
            map.put("status", record.getStatus().getName());
            map.put("remark", record.getRemark());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    /**
     * 检测加班记录是否存在时间交集
     * @param record
     */
    public void checkRepeat(OverTimeRecord record) {

    }

    public JsonVo updateRecord(int id){
        JsonVo jsonVo = new JsonVo();
        OverTimeRecord record = findOne(id);
        if (record.getStatus() != Status.abnormal) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("该加班申请不为异常状态");
        }else{
            AttendanceDetail detail = record.getDetail();
            if(detail == null){
                jsonVo.setStatus(false);
                jsonVo.setMsg("该加班没有对应的考勤明细");
            }else{
                record.setActualBeginDate(record.getDate());
                record.setActualEndDate(record.getEndDate());
                record.setActualTotalTime(record.getTotalTime());
                record.setStatus(Status.finish);
                record.setRemark("异常修改为正常");
                calculate.updateDetailByOverTimeRecord(detail,record);
                calculate.calculateTime(detail);
                super.update(record);
                detailService.update(detail);
            }

            jsonVo.setStatus(true);
            jsonVo.setMsg("修改成功");
        }
        return jsonVo;
    }
}
