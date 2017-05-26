package com.victory.hr.sys.controller;

import com.victory.hr.base.BaseCURDController;
import com.victory.hr.sys.entity.Resource;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.service.ResourceService;
import com.victory.hr.sys.service.RoleService;
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
 * Time:17:02
 */
@Controller
@RequestMapping(value = "/sys/resource")
public class ResourceController extends BaseCURDController<Resource,Integer> {

    private ResourceService getResourceService() {
        return (ResourceService) baseService;
    }

    public ResourceController() {
        setResourceIdentity("resource");
    }

    @Override
    protected void setCommonData(Model model,Resource resource) {
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping(value = "/jsonlist")
    public @ResponseBody
    List jsonList(){
        List<Resource> temp = getResourceService().findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (Resource resource : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", resource.getId() + "");
            map.put("name", resource.getName());
            list.add(map);
        }
        return list;
    }

}
