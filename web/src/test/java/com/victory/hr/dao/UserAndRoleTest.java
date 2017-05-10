package com.victory.hr.dao;

import com.victory.hr.po.UserPO;
import com.victory.hr.sys.controller.UserController;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import com.victory.hr.sys.service.RoleService;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.test.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajkx
 * Date: 2017/5/10.
 * Time:14:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = {
        "classpath:spring-config.xml",
//        "classpath:spring-test-config.xml",
        "classpath:spring-mvc-config.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserAndRoleTest{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserController userController;
    @Test
    public void testCreate() {
        User user = new User();
        user.setName("test");

        Set<User> userSet = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findOne(1);
        roleSet.add(role);
        user.setRoles(roleSet);
        userService.save(user);
    }

    @Test
    public void testUserController() {
        UserPO userPO = new UserPO();
        User user = new User();
        user.setName("test");
        userPO.setUser(user);
        userController.test(userPO);
    }

    @Test
    public void testDelete() {
        userService.delete(32);
    }
}
