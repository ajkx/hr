package com.victory.hr.sys.service;

import com.victory.hr.common.service.BaseService;
import com.victory.hr.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ajkx on 2017/5/7.
 */
@Service
public class RoleService extends BaseService<Role,Integer>{

    @Autowired
    private ResourceService resourceService;
}
