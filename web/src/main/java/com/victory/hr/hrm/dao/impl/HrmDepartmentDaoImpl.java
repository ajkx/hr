package com.victory.hr.hrm.dao.impl;


import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.dao.HrmDepartmentDao;
import com.victory.hr.hrm.dao.HrmResourceDao;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 职称数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
@Repository
public class HrmDepartmentDaoImpl extends BaseDaoImpl<HrmDepartment,Integer> implements HrmDepartmentDao {

    @Override
    public List<HrmDepartment> findBySubCompany(HrmSubCompany supCompany) {
        return find("select d from HrmDepartment d where d.subCompany = ?0",supCompany);
    }

    @Override
    public List<HrmDepartment> findRootDepBySubCompany(HrmSubCompany supCompany) {
        return find("select d from HrmDepartment d where d.subCompany = ?0 and (d.parent is null or d.parent = 0)",supCompany);
    }

    @Override
    public List<HrmDepartment> findByParent(HrmDepartment parent) {
        return find("select d from HrmDepartment d where d.parent = ?0",parent);
    }
}
