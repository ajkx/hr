package com.victory.hr.attendance.dao;



import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.common.dao.BaseDao;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.hrm.entity.HrmResource;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceDetailDao extends BaseDao<AttendanceDetail,Integer> {

    /**
     * 找出对应日期里的待计算状态的明细数据，用于跨天运算
     * @param date
     * @return
     */
    List<AttendanceDetail> findAcrossDayByDate(Date date);

    /**
     * 找出汇总数据
     * @param beginDate
     * @param endDate
     * @param resources
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo findAllCollect(Date beginDate, Date endDate, Collection resources, int pageNo, int pageSize);

    List<AttendanceDetail> findByHrmResourceAndDate(HrmResource resource, Date date);
}
