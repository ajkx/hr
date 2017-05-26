package com.victory.hr.attendance.service;

import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:48
 */
@Service
public class LevelRecordService extends BaseService<LevelRecord,Integer> {

    @Override
    public LevelRecord save(LevelRecord entity) {
        entity.setStatus(Status.normal);
        entity.setTotalTime(DateUtils.getTimeInterval(entity.getDate(), entity.getEndDate()));
        return super.save(entity);
    }

    @Override
    public void update(LevelRecord entity) {
        entity.setStatus(Status.normal);
        entity.setTotalTime(DateUtils.getTimeInterval(entity.getDate(), entity.getEndDate()));
        super.update(entity);
    }

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = baseDao.findAll(searchable);
        List<LevelRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (LevelRecord record : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("type", record.getType().getName());
            map.put("beginDate", record.getDate());
            map.put("endDate", record.getEndDate());
            map.put("sum", record.getTotalTime());
            map.put("reason", record.getReason());
            map.put("status", record.getStatus().getName());
            map.put("remark", record.getRemark());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
