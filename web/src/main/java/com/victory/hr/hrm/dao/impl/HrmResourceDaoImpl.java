package com.victory.hr.hrm.dao.impl;


import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.hrm.dao.HrmResourceDao;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmStatus;
import com.victory.hr.hrm.entity.HrmSubCompany;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * 职称数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
@Repository
public class HrmResourceDaoImpl extends BaseDaoImpl<HrmResource,Integer> implements HrmResourceDao {

    @Override
    public List<HrmResource> findByName(String[] names) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(HrmResource.class);
        criteria.createAlias("department", "dept");
        Disjunction dis = Restrictions.disjunction();
        for (String temp : names) {
            dis.add(Restrictions.ilike("name", temp, MatchMode.ANYWHERE));
            dis.add(Restrictions.ilike("dept.name", temp, MatchMode.ANYWHERE));

        }
        criteria.add(dis);
        criteria.addOrder(Order.asc("dept.id"));
        HrmStatus[] statuses = new HrmStatus[]{HrmStatus.probation,HrmStatus.offical,HrmStatus.late_probation,HrmStatus.temporary};
        criteria.add(Restrictions.in("status", statuses));
        return criteria.list();
    }

    @Override
    public List<HrmResource> findBySubCompany(HrmSubCompany subCompany) {
        return find("select r from HrmResource r where r.subCompany = ?0 and r.status in (0,1,2,3)",subCompany);
    }

    @Override
    public List<HrmResource> findByDepartment(HrmDepartment department) {
        return find("select r from HrmResource r where r.department = ?0 and r.status in (0,1,2,3)",department);
    }

    @Override
    public List<HrmResource> findByWorkingAndEntryDate(Date date) {
        return find("select r from HrmResource r where r.createDate <?0 and r.status in (0,1,2,3)",date.toString());
    }
}
