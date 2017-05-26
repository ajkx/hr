package com.victory.hr.attendance.dao.impl;



import com.victory.hr.attendance.dao.AttendanceDetailDao;
import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.common.dao.BaseDaoImpl;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.hrm.entity.HrmResource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
@Repository
public class AttendanceDetailDaoImpl extends BaseDaoImpl<AttendanceDetail,Integer> implements AttendanceDetailDao{
    @Override
    public List<AttendanceDetail> findAcrossDayByDate(Date date) {
        return find("select a from AttendanceDetail a where status = 3 and date <= ?0",date);
    }

    @Override
    public PageInfo findAllCollect(Date beginDate, Date endDate, Collection resources, int pageNo, int pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(AttendanceDetail.class);

        criteria.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0){
            criteria.add(Restrictions.in("resource", resources));
        }
        //获取总数
        criteria.setProjection(Projections.projectionList().add(Projections.countDistinct("resource")));
        long totals = (long) criteria.uniqueResult();

        criteria.setProjection(Projections.projectionList()
                .add(Projections.sum("should_attendance_day"))
                .add(Projections.sum("actual_attendance_day"))
                .add(Projections.sum("should_attendance_time"))
                .add(Projections.sum("actual_attendance_time"))
                .add(Projections.sum("lateCount"))
                .add(Projections.sum("lateTime"))
                .add(Projections.sum("earlyCount"))
                .add(Projections.sum("earlyTime"))
                .add(Projections.sum("absenteeismCount"))
                .add(Projections.sum("absenteeismTime"))
                .add(Projections.sum("overtime_normal"))
                .add(Projections.sum("overtime_weekend"))
                .add(Projections.sum("overtime_festival"))
                .add(Projections.sum("leave_personal"))
                .add(Projections.sum("leave_rest"))
                .add(Projections.sum("leave_business"))
                .add(Projections.groupProperty("resource"))
        );
        criteria.setFirstResult((pageNo - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        PageInfo pageInfo = new PageInfo(totals, criteria.list());
        return pageInfo;
    }

    @Override
    public List<AttendanceDetail> findByHrmResourceAndDate(HrmResource resource, Date date) {
        return find("select a from AttendanceDetail a where resource = ?0 and date = ?1",resource,date);
    }
}
