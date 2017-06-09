package com.victory.hr.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by ajkx
 * Date: 2017/6/5.
 * Time:11:53
 */
public class JobTest extends QuartzJobBean{
    private int i = 1;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("啊啊啊啊啊啊啊啊啊啊啊 "+i);
        i++;
    }
}
