package com.victory.hr.hrm.service;

import com.victory.hr.hrm.dao.HrmDepartmentDao;
import com.victory.hr.hrm.dao.HrmSubCompanyDao;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmSubCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/13.
 * Time:15:22
 */
@Service
public class OrganizationService {
    @Autowired
    private HrmSubCompanyDao subCompanyDao;

    @Autowired
    private HrmDepartmentDao departmentDao;


    public List<HrmSubCompany> findSubCompanyByParent(HrmSubCompany subCompany) {
        return subCompanyDao.findByParent(subCompany);
    }

    public List<HrmDepartment> findRootDepBySubCompany(HrmSubCompany subCompany) {
        return departmentDao.findRootDepBySubCompany(subCompany);
    }

    public List<HrmDepartment> findDepByParent(HrmDepartment department) {
        return departmentDao.findByParent(department);
    }
}
