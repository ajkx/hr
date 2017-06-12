package com.victory.hr.service;

import com.victory.hr.attendance.enums.RecordType;
import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Pageable;
import com.victory.hr.common.search.SearchFilter;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.test.BaseTest;
import org.hibernate.criterion.MatchMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:11:36
 */
public class BaseServiceTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() {
        Searchable searchable = new Searchable();
        //配置分页信息
        Pageable pageable = new Pageable();
        pageable.setcPage(1);
        pageable.setpSize(10);
        searchable.setPageable(pageable);

        //配置查询条件
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setKey("name");
        searchFilter.setOperator("ilike");
        searchFilter.setValue("2");
        Object[] objects = new Object[]{searchFilter.getKey(),searchFilter.getValue(),MatchMode.ANYWHERE};
        searchFilter.setObjects(objects);
        Class[] clazz = new Class[]{String.class, String.class, MatchMode.class};
        searchFilter.setClazz(clazz);

        SearchFilter searchFilter1 = new SearchFilter();
        searchFilter1.setKey("name");
        searchFilter1.setOperator("eq");
        searchFilter1.setValue("admin");
        Object[] objects1 = new Object[]{searchFilter1.getKey(),searchFilter1.getValue()};
        searchFilter1.setObjects(objects1);
        Class[] clazz1 = new Class[]{String.class, Object.class };
        searchFilter1.setClazz(clazz1);

        List<SearchFilter> searchFilterList = new ArrayList<>();
//        searchFilterList.add(searchFilter);
        searchFilterList.add(searchFilter1);

        searchable.setSearchFilters(searchFilterList);
        PageInfo pageInfo = userService.findAll(searchable);
        System.out.println(pageInfo.getTotals());
    }

    @Test
    public void testStatus() {
        Status status = Status.valueOf("normal");
        System.out.println(status);
    }
}
