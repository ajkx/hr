package com.victory.hr.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/3.
 * Time:10:13
 */
public interface BaseDao<T,ID extends Serializable> {

    /**
     * 保存持久化实体
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 删除实体
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据ID删除实体
     * @param id
     */
    void delete(ID id);

    /**
     * 更新实体
     * @param entity
     */
    void update(T entity);

    /**
     * 根据传入ID找出对应实体
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * 找出所有的实体
     * @return
     */
    List<T> findAll();

    /**
     * 找出该表总条数
     * @return
     */
    long findCount();
}
