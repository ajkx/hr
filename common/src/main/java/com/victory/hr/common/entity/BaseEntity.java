package com.victory.hr.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 抽象实体基类，提供统一的ID，和基本的方法（equals,hashcode,toString）
 * Created by ajkx
 * Date: 2017/5/3.
 * Time:9:33
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Override
    public ID getId(){return id;}

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}
