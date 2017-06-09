package com.victory.hr.schedule.dao.impl;

import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.schedule.dao.JobBeanCfgDao;
import com.victory.hr.schedule.entity.JobBeanCfg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/6/2.
 * Time:16:14
 */
@Repository
public class JobBeanCigDaoImpl extends BaseDaoImpl<JobBeanCfg,Integer> implements JobBeanCfgDao{
    @Override
    public JobBeanCfg findByJobClass(String jobClass) {
        List<JobBeanCfg> list = find("select j from JobBeanCfg j where jobClass = ?0", jobClass);
        return list.size() > 0 ? list.get(0) : null;
    }
}
