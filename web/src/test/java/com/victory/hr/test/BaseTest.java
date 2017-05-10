package com.victory.hr.test;

import com.victory.hr.common.entity.AbstractEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-14 下午4:25
 * <p>Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = {
        "classpath:spring-config.xml",
//        "classpath:spring-test-config.xml",
        "classpath:spring-mvc-config.xml"
})
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

}

