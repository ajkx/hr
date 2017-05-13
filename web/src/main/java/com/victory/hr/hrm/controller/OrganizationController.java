package com.victory.hr.hrm.controller;

import com.victory.hr.hrm.entity.HrmSubCompany;
import com.victory.hr.hrm.service.HrmDepartmentService;
import com.victory.hr.hrm.service.HrmSubCompanyService;
import com.victory.hr.hrm.service.OrganizationService;
import com.victory.hr.util.TreeNodeUtil;
import com.victory.hr.vo.JsonTreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/13.
 * Time:15:46
 */
@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Autowired
    private HrmSubCompanyService subCompanyService;

    @Autowired
    private HrmDepartmentService departmentService;

    @Autowired
    private OrganizationService organizationService;

    /**
     * 返回 选择部门 分部或人员的多选模态框
     * @return
     */
    @RequestMapping(value = "/modal/list")
    public String modalList() {
        return "/hrm/organization/modal/HrmOrganizationAndResource";
    }

    @RequestMapping(value = "/tree")
    public
    @ResponseBody
    List<JsonTreeData> getTree(String type) {
        List<HrmSubCompany> list = subCompanyService.findRootSubCompany();
        List<JsonTreeData> tree = TreeNodeUtil.convertSubTreeList(list, organizationService, type);
        return tree;
    }
}
