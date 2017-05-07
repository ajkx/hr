package com.victory.hr.common.dao;


import com.victory.hr.common.utils.HibernateUtils;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/6.
 * Time:10:54
 */
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected final Class<T> entityClass;

    public BaseDaoImpl() {
        //通过反射获取T的类型信息实例
        this.entityClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }


    @Override
    public T save(T entity) {
        HibernateUtils.getSession(sessionFactory).save(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        HibernateUtils.getSession(sessionFactory).delete(entity);
    }

    @Override
    public void delete(ID id) {
        HibernateUtils.getSession(sessionFactory).createQuery("delete " + entityClass.getSimpleName() + " en where en.id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public void update(T entity) {
        HibernateUtils.getSession(sessionFactory).update(entity);
    }

    @Override
    public T findOne(ID id) {
        List<T> list = find("select en from " + entityClass.getSimpleName() +  "en where en.id = ?0",id);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }else{
            return null;
        }

    }

    @Override
    public List<T> findAll() {
        return find("select en from "+entityClass.getSimpleName()+" en");
    }

    @Override
    public long findCount() {
        Query query = HibernateUtils.getSession(sessionFactory).createQuery("select count(*) from "+entityClass.getSimpleName());
        return (long) query.uniqueResult();
    }

    /**
     * 自定义find方法
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        List<T> list = null;
        try {
            list = getSessionFactory().getCurrentSession().createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... params) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + "", params[i]);
        }
        return (List<T>) query.list();
    }
}
