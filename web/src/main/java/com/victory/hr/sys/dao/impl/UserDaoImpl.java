package com.victory.hr.sys.dao.impl;

import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.sys.dao.UserDao;
import com.victory.hr.sys.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ajkx on 2017/5/7.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,Integer> implements UserDao{
    public User findByUserName(String username) {
        List<User> list = find("select u from User u where u.name = ?0", username);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
