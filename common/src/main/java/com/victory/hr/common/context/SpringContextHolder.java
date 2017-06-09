package com.victory.hr.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * Created by ajkx
 * Date: 2017/6/3.
 * Time:10:37
 *
 * 获取Spring上下文
 */
public class SpringContextHolder implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static <T> T gerBean(Class<T> type) {
        Assert.notNull(applicationContext,"SpringContextHolder.applicationContext is null");
        return applicationContext.getBean(type);
    }
}
