package com.victory.hr.common;

import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.common.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ajkx on 2017/5/7.
 */
public abstract class BaseService<T extends AbstractEntity,ID extends Serializable> {

    @Autowired
    protected BaseDao<T, ID> baseDao;

    public T save(T entity) {
        return baseDao.save(entity);
    }

    public void update(T entity) {
        baseDao.update(entity);
    }

    public void delete(T entity) {
        baseDao.delete(entity);
    }

    public void delete(ID id) {
        baseDao.delete(id);
    }

    public T findOne(ID id) {
        return baseDao.findOne(id);
    }

    public List<T> findAll() {
        return baseDao.findAll();
    }

    public long count() {
        return baseDao.findCount();
    }
}
