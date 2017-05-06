package com.victory.hr.common.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by ajkx
 * Date: 2017/5/6.
 * Time:11:07
 */
public class HibernateUtils {

    public static Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }
}
