package com.victory.hr.hrm.dao;

import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmSubCompany;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:06
 */
public interface HrmSubCompanyDao extends BaseDao<HrmSubCompany,Integer>{


    List<HrmSubCompany> findRootSubCompany();

    List<HrmSubCompany> findByParent(HrmSubCompany subCompany);
}
