package com.victory.hr.hrm.dao.impl;


import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.dao.HrmResourceDao;
import com.victory.hr.hrm.dao.HrmSubCompanyDao;
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
public class HrmSubCompanyDaoImpl extends BaseDaoImpl<HrmSubCompany,Integer> implements HrmSubCompanyDao {

    @Override
    public List<HrmSubCompany> findRootSubCompany() {
        return find("select s from HrmSubCompany s where s.parent = 0 and (cancel is null or cancel = 0)");
    }

    @Override
    public List<HrmSubCompany> findByParent(HrmSubCompany subCompany) {
        return find("select s from HrmSubCompany s where s.parent = ?0",subCompany);
    }
}
