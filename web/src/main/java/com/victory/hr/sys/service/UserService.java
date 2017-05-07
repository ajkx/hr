package com.victory.hr.sys.service;

import com.victory.hr.common.BaseService;
import com.victory.hr.sys.dao.UserDao;
import com.victory.hr.sys.entity.Resource;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajkx on 2017/5/7.
 */
@Service
public class UserService extends BaseService<User,Long>{

    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

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
