package com.victory.hr.hrm.controller;

import com.victory.hr.base.BaseCURDController;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;
import com.victory.hr.hrm.service.HrmDepartmentService;
import com.victory.hr.hrm.service.HrmResourceService;
import com.victory.hr.hrm.service.HrmSubCompanyService;
import com.victory.hr.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:9:28
 */
@Controller
@RequestMapping(value = "hrm/resource")
public class HrmResourceController extends BaseCURDController<HrmResource, Integer> {

    @Autowired
    private HrmSubCompanyService subCompanyService;

    @Autowired
    private HrmDepartmentService departmentService;

    @Override
    protected void setCommonData(Model model,HrmResource resource) {
    }

    public HrmResourceService getService() {
        return (HrmResourceService) baseService;
    }

    public HrmResourceController() {
        setResourceIdentity("hrmresource");
    }

    /**
     * 单选人员模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "hrmresource:view")
    @RequestMapping("/modal/single")
    public String modalSingle(Model model) {
        return viewName("/modal/single");
    }

    /**
     * 多选人员模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "hrmresource:view")
    @RequestMapping("/modal/multi")
    public String modalMulti(Model model) {
        return viewName("/modal/multi");
    }

    /**
     * 人员模态框中读取已选择的人员信息
     * @param resourceStr
     * @return
     */
    @RequestMapping("/modal/list")
    public
    @ResponseBody
    JsonVo listByStr(String resourceStr) {
        JsonVo jsonVo = new JsonVo();
        if(resourceStr == null){
            jsonVo.setStatus(false);
        }else{
            String[] resources = resourceStr.split(",");
            List<HrmResource> resourceList = new ArrayList<>();

            for (String temp : resources) {
                if(temp.equals("")) continue;
                HrmResource resource = getService().findOne(Integer.parseInt(temp));
                if(resource != null){
                    resourceList.add(resource);
                }
            }
            jsonVo.setStatus(true);
            jsonVo.put("data", getResourceList(resourceList));
        }
        return jsonVo;
    }

    /**
     * 模态框中的 人员和部门模糊搜索
     * @param name
     * @return
     */
    @RequiresPermissions(value = "resource:view")
    @RequestMapping("/modal/name")
    public @ResponseBody JsonVo listByName(String name) {
        List<HrmResource> resourceList = getService().findByName(name);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true);
        jsonVo.put("data", getResourceList(resourceList));
        return jsonVo;
    }

    /**
     * 查找分部所有人
     * @param id
     * @return
     */
    @RequiresPermissions(value = "resource:view")
    @RequestMapping("subcompany/{id}")
    public @ResponseBody JsonVo listBySubCompany(@PathVariable int id) {
        HrmSubCompany subCompany = subCompanyService.findOne(id);
        List<HrmResource> resources = null;
        JsonVo jsonVo = new JsonVo();
        if(subCompany != null){
            resources =getService().findBySubCompany(subCompany);
            jsonVo.setStatus(true);
            jsonVo.put("data", getResourceList(resources));
        }else{
            jsonVo.setStatus(false).setMsg("传入参数错误！");
        }
        return jsonVo;
    }

    /**
     * 查找部门所有人
     * @param id
     * @return
     */
    @RequiresPermissions(value = "resource:view")
    @RequestMapping("department/{id}")
    public @ResponseBody JsonVo listByDepartment(@PathVariable int id) {
        HrmDepartment department = departmentService.findOne(id);
        List<HrmResource> resources = null;
        JsonVo jsonVo = new JsonVo();
        if(department != null){
            resources =getService().findByDepartment(department);

            jsonVo.setStatus(true);
            jsonVo.put("data", getResourceList(resources));
        }else{
            jsonVo.setStatus(false).setMsg("传入参数错误！");
        }
        return jsonVo;
    }



    public List getResourceList(List<HrmResource> resources) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (HrmResource resource : resources) {
            Map<String, String> temp = new HashMap<>();
            temp.put("id", resource.getId() + "");
            temp.put("name", resource.getName());
            String subCompany = "";
            if (resource.getSubCompany() != null) {
                subCompany = resource.getSubCompany().getName();
            }
            String department = "";
            if (resource.getSubCompany() != null) {
                department = resource.getDepartment().getName();
            }

            temp.put("subcompany", subCompany);
            temp.put("department", department);
            mapList.add(temp);
        }
        return mapList;
    }

}
