package com.victory.hr.util;

import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmSubCompany;
import com.victory.hr.hrm.service.OrganizationService;
import com.victory.hr.vo.JsonTreeData;
import com.victory.hr.vo.JsonTreeState;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取树节点集合
 *
 * @author ajkx_Du
 * @create 2016-11-23 17:21
 */
public class TreeNodeUtil {


    public static List<JsonTreeData> convertSubTreeList(List<HrmSubCompany> list, OrganizationService organizationService, String type) {
        List<JsonTreeData> tree = new ArrayList<>();
            for (HrmSubCompany subCompany : list) {
                //如果是封存的，则继续下一循环
                if(subCompany.getCancel() != null && subCompany.getCancel()){
                    continue;
                }
                JsonTreeData temp = new JsonTreeData();
                //temp.setId(subCompany.getId().toString());
                //temp.setType("sub");
                temp.setText(subCompany.getName());
                //temp.setPid(subCompany.getParent().toString());
                List<JsonTreeData> sublist =  convertSubTreeList(organizationService.findSubCompanyByParent(subCompany),organizationService,type);
                List<JsonTreeData> deplist = convertDepTreeList(organizationService.findRootDepBySubCompany(subCompany),organizationService,type);
                deplist.addAll(sublist);
                temp.setIcon("fa fa-home");
                if(subCompany.getParent() == null){
                    JsonTreeState state = new JsonTreeState();
                    state.setExpanded(true);
                    temp.setState(state);
                }
                String link = "";
                if(type.equals("organization")){
                    link = "/organization/subcompany/" + subCompany.getId() + ".html";
                }else if(type.equals("resource")){
                    link = "/hrm/resource/subcompany/" + subCompany.getId();
//                    checked属性原设定为checkbox
                }else if(type.equals("checked")){
                   // temp.setSelectable(false);
                    link = "/hrm/resource/subcompany/" + subCompany.getId();
                }
                temp.setHref(link);
                temp.setNodes(deplist.size() == 0 ? null : deplist);
                tree.add(temp);
            }
        return tree;
    }

    public static List<JsonTreeData> convertDepTreeList(List<HrmDepartment> list,OrganizationService organizationService,String type) {
        List<JsonTreeData> tree = new ArrayList<>();
        for (HrmDepartment department : list) {
            //如果是封存的，则继续下一循环
            if ( department.getCancel() != null && department.getCancel()) {
                continue;
            }
            JsonTreeData temp = new JsonTreeData();
            //temp.setId(department.getId().toString());
            //temp.setType("dep");
            temp.setText(department.getName());
            temp.setIcon("fa fa-folder");
            String link = "";
            if(type.equals("organization")){
                link = "/organization/department/" + department.getId() + ".html";
            }else if(type.equals("resource")){
                link = "/hrm/resource/department/" + department.getId();
            } else if (type.equals("checked")) {
                link = "/hrm/resource/department/" + department.getId();
            }
            temp.setHref(link);
            //temp.setPid(department.getParent().toString());
            List<JsonTreeData> deplist = convertDepTreeList(organizationService.findDepByParent(department),organizationService,type);
            temp.setNodes(deplist.size() == 0 ? null : deplist);
            tree.add(temp);
        }
        return tree;
    }











}
