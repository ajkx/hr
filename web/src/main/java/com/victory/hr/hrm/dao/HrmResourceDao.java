package com.victory.hr.hrm.dao;

import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:06
 */
public interface HrmResourceDao extends BaseDao<HrmResource,Integer>{

    /**
     * 传入字符数组，模糊查找人员名称，部门名称
     * @param names
     * @return
     */
    List<HrmResource> findByName(String[] names);

    /**
     * 通过分部查询
     * @param subCompany
     * @return
     */
    List<HrmResource> findBySubCompany(HrmSubCompany subCompany);

    /**
     * 通过部门查询
     * @param department
     * @return
     */
    List<HrmResource> findByDepartment(HrmDepartment department);

}
