package com.victory.hr.sys.dao;

import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.sys.entity.User;

/**
 * Created by ajkx on 2017/5/7.
 */
public interface UserDao extends BaseDao<User,Integer> {

    public User findByUserName(String username);
}
