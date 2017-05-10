package com.victory.hr.sys.controller;

import com.victory.hr.base.BaseCURDController;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.service.RoleService;
import com.victory.hr.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/5/9.
 * Time:16:58
 */
@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends BaseCURDController<Role,Integer>{

    private RoleService getRoleService() {
        return (RoleService) baseService;
    }

    public RoleController() {
        setResourceIdentity("role");
    }

    @Override
    protected void setCommonData(Model model) {
    }

    @RequiresPermissions(value = "role:view")
    @RequestMapping(value = "/jsonlist")
    public @ResponseBody
    List jsonList(){
        List<Role> temp = getRoleService().findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (Role role : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", role.getId() + "");
            map.put("name", role.getName());
            list.add(map);
        }
        return list;
    }


}
