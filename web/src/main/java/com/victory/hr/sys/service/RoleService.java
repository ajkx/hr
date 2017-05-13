package com.victory.hr.sys.service;

import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.sys.entity.Resource;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ajkx on 2017/5/7.
 */
@Service
public class RoleService extends BaseService<Role,Integer>{

    @Autowired
    private ResourceService resourceService;

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<Role> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Role role : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            map.put("description", role.getDescription());
            Set<User> userSet = role.getUsers();
            List<Map<String, Object>> userList = new ArrayList<>();
            for (User user : userSet) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", user.getId());
                temp.put("name", user.getName());
                userList.add(temp);
            }

            map.put("users", userList);

            Set<Resource> resourceSet = role.getResources();
            List<Map<String, Object>> resList = new ArrayList<>();
            for (Resource resource: resourceSet) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", resource.getId());
                temp.put("name", resource.getName());
                resList.add(temp);
            }
            map.put("resources", resList);

            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    @Override
    public void update(Role entity) {
        int id = entity.getId();
        Role role = findOne(id);
        role.setName(entity.getName());
        role.setDescription(entity.getDescription());
        role.setResources(entity.getResources());
        super.update(role);
    }
}
