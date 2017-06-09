package com.victory.hr.attendance.job;

import com.victory.hr.attendance.service.AttendanceCalculate;
import com.victory.hr.common.utils.SpringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by ajkx
 * Date: 2017/6/9.
 * Time:11:19
 * <p>
 * 计算处于待计算状态的 请假，加班，异常的记录
 */
public class CalAttendanceResult extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(CalAttendanceResult.class);

    public CalAttendanceResult() {
        System.out.println("新建");
    }
    private static boolean isRunning = false;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行定时方法1");
        System.out.println(isRunning);
        if (!isRunning) {
            System.out.println("true执行定时方法");
            isRunning = true;
            logger.info("开始执行考勤计算");
//        先计算请假的
            AttendanceCalculate calculate = SpringUtils.getBean(AttendanceCalculate.class);
            calculate.calculateAll();
            logger.info("考勤计算成功");
            isRunning = false;
        }

    }
}
