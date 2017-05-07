package com.victory.hr.sys.service;

import com.victory.hr.common.BaseService;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ajkx on 2017/5/7.
 */
@Service
public class RoleService extends BaseService<Role,Long>{

    @Autowired
    private ResourceService resourceService;
}
