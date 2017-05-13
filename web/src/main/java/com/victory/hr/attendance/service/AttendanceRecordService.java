package com.victory.hr.attendance.service;

import com.victory.hr.attendance.entity.AttendanceRecord;
import com.victory.hr.attendance.enums.RecordType;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.SearchFilter;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.sys.entity.Resource;
import com.victory.hr.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/10.
 * Time:14:29
 */
@Service
public class AttendanceRecordService extends BaseService<AttendanceRecord,Integer> {

    @Override
    public PageInfo findAll(Searchable searchable) {

        //区别是考勤机读取还是签卡
        String type = searchable.getRequest().getParameter("type");
        SearchFilter filter = new SearchFilter();
        filter.setOperator("eq");
        filter.setClazz(new Class[]{String.class,Object.class});
        if("manual".equals(type)){
            filter.setObjects(new Object[]{"type",RecordType.manual});
            searchable.getSearchFilters().add(filter);
        } else if ("machine".equals(type)) {
            filter.setObjects(new Object[]{"type",RecordType.machine});
            searchable.getSearchFilters().add(filter);
        }

        PageInfo info = super.findAll(searchable);
        List<AttendanceRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (AttendanceRecord record : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("date", record.getDate());
            map.put("reason",record.getReason());
            map.put("machineNo", record.getMachineNo());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
