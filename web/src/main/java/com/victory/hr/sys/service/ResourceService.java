package com.victory.hr.sys.service;

import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.sys.dao.ModuleDao;
import com.victory.hr.sys.entity.Module;
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
public class ResourceService extends BaseService<Resource,Integer>{

    @Autowired
    private ModuleDao moduleDao;

    @Autowired
    private UserService userService;


    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<Resource> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Resource resource : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", resource.getId());
            map.put("name", resource.getName());
            map.put("description",resource.getDescription());
            Set<Role> roleSet = resource.getRoles();
            List<Map<String, Object>> roles = new ArrayList<>();
            for (Role role : roleSet) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", role.getId());
                temp.put("name", role.getName());
                roles.add(temp);
            }
            map.put("roles", roles);
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public Set<Resource> findByUsername(String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        Set<Role> roles = user.getRoles();
        Set<Resource> resources = new HashSet<>();
        for (Role role : roles) {
            resources.addAll(role.getResources());
        }
        return resources;
    }
    public Map<Module, ArrayList<Resource>> findMenus(String username) {
        Set<Resource> allResources = findByUsername(username);
        //对菜单进行排序
        List<Resource> resourceList = new ArrayList<>(allResources);
        Collections.sort(resourceList, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        LinkedHashMap<Module,ArrayList<Resource>> menus = new LinkedHashMap<>();

        List<Resource> resources = new ArrayList<>();
        List<Module> modules = new ArrayList<>();

        //获取该用户有为menu的资源
        for (Resource resource : resourceList) {
            if (resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!modules.contains(resource.getModule()) && resource.getModule() != null){
                modules.add(resource.getModule());
            }
            resources.add(resource);
        }

        for (Module module : modules) {
            ArrayList<Resource> list = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource.getModule().getId() == module.getId()) {
                    list.add(resource);
                }
            }
            menus.put(module, list);
        }
        return menus;
    }

}
