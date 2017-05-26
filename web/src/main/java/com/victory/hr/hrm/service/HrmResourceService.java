package com.victory.hr.hrm.service;

import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.StringUtils;
import com.victory.hr.hrm.dao.HrmResourceDao;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:09
 */
@Service
public class HrmResourceService extends BaseService<HrmResource,Integer>{

    @Autowired
    private HrmSubCompanyService subCompanyService;

    @Autowired
    private HrmDepartmentService departmentService;

    public HrmResourceDao getDao() {
        return (HrmResourceDao) baseDao;
    }
    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<HrmResource> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (HrmResource resource : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",resource.getId());
            map.put("name", StringUtils.nullString(resource.getName()));
            map.put("workCode",StringUtils.nullString(resource.getWorkCode()));
            map.put("subCompany", resource.getSubCompany() == null ? "" :resource.getSubCompany().getName());
            map.put("department", resource.getDepartment() == null ? "" :resource.getDepartment().getName());
            String manager = resource.getManager();
            if (manager == null) {
                manager = "";
            }else{
                String[] array = manager.split(",");
                for(int i = 0; i < array.length; i++) {
                    if("".equals(array[i])) continue;
                    int managerId = Integer.parseInt(array[i]);
                    HrmResource managerRe = findOne(managerId);
                    if (managerRe != null) {
                        manager = managerRe.getName();
                    }else{
                        manager = "";
                    }
                    break;
                }
            }
            map.put("manager", manager);
            map.put("jobPosition", resource.getJobTitle() == null ? "" :resource.getJobTitle().getName());
            map.put("status", resource.getStatus().getName());
            map.put("mobile", StringUtils.nullString(resource.getMobile()));
            map.put("email", StringUtils.nullString(resource.getEmail()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public List<HrmResource> findByName(String name) {
        String[] names = name.split(",");
        return getDao().findByName(names);
    }


    public List<HrmResource> findBySubCompany(HrmSubCompany subCompany) {
        return getDao().findBySubCompany(subCompany);
    }

    public List<HrmResource> findByDepartment(HrmDepartment department) {
        return getDao().findByDepartment(department);
    }

    /**
     * 分割前台传入的人员,部门,分部字段，并查询到相应的人员
     *
     * * @param str
     * @return
     */
    public Set<HrmResource> splitForHrmResource(String str) {
        String[] array = str.split(",");
        Set<HrmResource> resources = new HashSet<>();
        for (String temp : array) {
            if(temp.equals("")) continue;
            //员工ID
            if(temp.contains("r")){
                int id = Integer.parseInt(temp.substring(1, temp.length()));
                HrmResource resource = findOne(id);
                resources.add(resource);
            }
            //分部ID
            else if(temp.contains("s")){
                int id = Integer.parseInt(temp.substring(1, temp.length()));
                List<HrmResource> list = findBySubCompany(subCompanyService.findOne(id));
                resources.addAll(list);
            }
            //部门ID
            else if(temp.contains("d")){
                int id = Integer.parseInt(temp.substring(1, temp.length()));
                List<HrmResource> list = findByDepartment(departmentService.findOne(id));
                resources.addAll(list);
            }else{
                HrmResource resource = findOne(Integer.parseInt(temp));
                resources.add(resource);
            }
        }
        return resources;
    }

    public List<HrmResource> findAllWorkingAndEntryDate(Date date) {
        return getDao().findByWorkingAndEntryDate(date);
    }
}
