package com.victory.hr.dao;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.attendance.entity.AttendanceGroup;
import com.victory.hr.attendance.entity.OverTimeRecord;
import com.victory.hr.attendance.service.AttendanceCalculate;
import com.victory.hr.attendance.service.AttendanceGroupService;
import com.victory.hr.hrm.dao.HrmResourceDao;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;
import com.victory.hr.hrm.service.HrmResourceService;
import com.victory.hr.hrm.service.HrmSubCompanyService;
import com.victory.hr.po.UserPO;
import com.victory.hr.sys.controller.UserController;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import com.victory.hr.sys.service.RoleService;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.test.BaseTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private HrmSubCompanyService subCompanyService;

    @Autowired
    private HrmResourceService resourceService;

    @Autowired
    private AttendanceGroupService groupService;

    @Autowired
    private AttendanceCalculate calculate;

    @Autowired
    private SessionFactory sessionFactory;

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
    public void testCal() {
        calculate.calculateAll();
    }

    @Test
    public void testGroup() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        AttendanceGroup group = (AttendanceGroup) session.get(AttendanceGroup.class,15);

//        HrmResource resource = (HrmResource) session.get(HrmResource.class,2496);

        HrmResource resource1 = new HrmResource();
        resource1.setId(4);

//        resource1 = (HrmResource) session.get(HrmResource.class, resource1.getId());
//        resource1.setAttendanceGroup(group);
        Set<HrmResource> resourceSet = new HashSet<>();
        resourceSet.add(resource1);
        group.setDescription("Sdfa");
        group.setResources(resourceSet);

        session.update(group);
//        resource.setAttendanceGroup(group);
//        session.saveOrUpdate(resource);

//        session.delete(group);
        tx.commit();
        session.close();
    }

    @Test
    public void testDate() throws ParseException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        AttendanceDetail detail = (AttendanceDetail) session.get(AttendanceDetail.class, 16);
        OverTimeRecord record = (OverTimeRecord) session.get(OverTimeRecord.class, 3);
        detail.setOverTimeRecords(null);
        session.update(detail);
//        System.out.println(detail.getOverTimeRecords());
//        System.out.println(record.getDetail());
        System.out.println("ad");
    }
}
