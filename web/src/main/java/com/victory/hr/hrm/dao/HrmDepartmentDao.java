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
public interface HrmDepartmentDao extends BaseDao<HrmDepartment,Integer>{

    /**
     * 根据上级分部找寻下属所有部门
     * @param supCompany
     * @return
     */
    List<HrmDepartment> findBySubCompany(HrmSubCompany supCompany);

    /**
     * 找分部下的根部门
     * @param supCompany
     * @return
     */
    List<HrmDepartment> findRootDepBySubCompany(HrmSubCompany supCompany);

    /**
     * 通过上级分部来找下属部门
     * @param parent
     * @return
     */
    List<HrmDepartment> findByParent(HrmDepartment parent);
}
