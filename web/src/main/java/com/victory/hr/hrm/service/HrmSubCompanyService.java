package com.victory.hr.hrm.service;

import com.victory.hr.common.service.BaseService;
import com.victory.hr.hrm.dao.HrmSubCompanyDao;
import com.victory.hr.hrm.entity.HrmSubCompany;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:09
 */
@Service
public class HrmSubCompanyService extends BaseService<HrmSubCompany,Integer>{

    public HrmSubCompanyDao getDao() {
        return (HrmSubCompanyDao) baseDao;
    }
    public List<HrmSubCompany> findRootSubCompany() {
        return getDao().findRootSubCompany();
    }
}
