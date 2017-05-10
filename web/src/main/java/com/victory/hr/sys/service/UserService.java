package com.victory.hr.sys.service;

import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.sys.dao.UserDao;
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
public class UserService extends BaseService<User,Integer>{

    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = super.findAll(searchable);
        List<User> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (User user : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            HrmResource hrmResource = user.getHrmResource();
            Map<String, Object> hrm = new HashMap<>();
            if (hrmResource == null) {
                hrm.put("id","");
                hrm.put("name", "");
            }else{
                hrm.put("id",hrmResource.getId());
                hrm.put("name", hrmResource.getName());

            }
            map.put("hrmResource", hrm);
            List<Map<String, Object>> roles = new ArrayList<>();
            System.out.println(user.getRoles().size());
            for (Role role : user.getRoles()) {
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

    public UserDao getUserDao() {
        return (UserDao) baseDao;
    }
    /**
     * 创建用户，加密密码
     * @param user
     * @return
     */
    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        save(user);
        return user;
    }

    public User findByUserName(String username) {
        return getUserDao().findByUserName(username);
    }

    public Set<String> findRoles(String username) {
        User user = findByUserName(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        return roles;
    }
    /**
     * 返回对应用户的权限字符串集合
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        User user = findByUserName(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }

        Set<Role> roles = user.getRoles();

        Set<String> permissions = new HashSet<>();
        for (Role role : roles) {
            Set<Resource> resources = role.getResources();
            for (Resource resource : resources) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }
}
