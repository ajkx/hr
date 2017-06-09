package com.victory.hr.schedule.service;

import com.google.common.collect.Maps;
import com.victory.hr.attendance.entity.LevelRecord;
import com.victory.hr.common.exception.ServiceException;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.utils.SpringUtils;
import com.victory.hr.schedule.ExtSchedulerFactoryBean;
import com.victory.hr.schedule.dao.JobBeanCfgDao;
import com.victory.hr.schedule.entity.JobBeanCfg;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/6/2.
 * Time:16:25
 */
@Service
public class JobBeanCfgService extends BaseService<JobBeanCfg,Integer>{

    private static Logger logger = LoggerFactory.getLogger(JobBeanCfgService.class);

    private JobBeanCfgDao getDao() {
        return (JobBeanCfgDao) baseDao;
    }

    public JobBeanCfg findByJobClass(String jobClass) {
        return getDao().findByJobClass(jobClass);
    }

    @SuppressWarnings({ "unused", "unchecked" })
    public Map<Trigger, SchedulerFactoryBean> findAllTriggers() {
        Map<Trigger, SchedulerFactoryBean> allTriggers = Maps.newLinkedHashMap();

        try {
            SchedulerFactoryBean quartzRAMScheduler = SpringUtils.getBean("&quartzRAMScheduler");
            if (quartzRAMScheduler != null) {
                for (Trigger trigger : (List<Trigger>)FieldUtils.readField(quartzRAMScheduler, "triggers", true)) {
                    allTriggers.put(trigger, quartzRAMScheduler);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Quartz trigger schedule error", e);
        }
        return allTriggers;
    }

    @Override
    public void update(JobBeanCfg entity) {
        try {
            Map<Trigger, SchedulerFactoryBean> allTriggers = findAllTriggers();
            for (Map.Entry<Trigger, SchedulerFactoryBean> m : allTriggers.entrySet()) {
                CronTrigger cronTrigger = (CronTrigger) m.getKey();
                ExtSchedulerFactoryBean scheduleFactoryBean = (ExtSchedulerFactoryBean) m.getValue();
                Scheduler scheduler = scheduleFactoryBean.getScheduler();
                //找出对应的类名的trigger
                if (cronTrigger.getJobKey().getName().equals(entity.getJobClass()) && !entity.getCronExpression().equals(cronTrigger.getCronExpression())) {
                    CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(cronTrigger.getKey())
                            .withSchedule(CronScheduleBuilder.cronSchedule(entity.getCronExpression())).build();
                    String oldCronExpression = cronTrigger.getCronExpression();
                    logger.info("RescheduleJob : {}, CRON from {} to {}", cronTrigger.getKey(), oldCronExpression,
                            cronTrigger.getCronExpression());
                    scheduler.rescheduleJob(newTrigger.getKey(), newTrigger);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Quartz trigger schedule error", e);
        }
        super.update(entity);
    }

    @Override
    public void delete(JobBeanCfg entity) {
        try{
            Map<Trigger, SchedulerFactoryBean> allTriggers = findAllTriggers();
            for (Map.Entry<Trigger, SchedulerFactoryBean> me : allTriggers.entrySet()) {
                CronTrigger cronTrigger = (CronTrigger) me.getKey();
                ExtSchedulerFactoryBean schedulerFactoryBean = (ExtSchedulerFactoryBean) me.getValue();
                Scheduler scheduler = schedulerFactoryBean.getScheduler();
                if (cronTrigger.getJobKey().getName().equals(entity.getJobClass())) {
                    logger.info("UnscheduleJob from quartzClusterScheduler: {}", cronTrigger.getJobKey());
                    scheduler.unscheduleJob(cronTrigger.getKey());
                    break;
                }
            }
        }catch (Exception e) {
            throw new ServiceException("Quartz trigger schedule error", e);
        }
        super.delete(entity);
    }

    @Override
    public PageInfo findAll(Searchable searchable) {
        PageInfo info = baseDao.findAll(searchable);
        List<JobBeanCfg> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        Map<Trigger, SchedulerFactoryBean> allTriggers = findAllTriggers();
        for (JobBeanCfg beanCfg : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", beanCfg.getId());
            map.put("jobClass", beanCfg.getJobClass());
            map.put("cronExpression", beanCfg.getCronExpression());
            map.put("autoStart", beanCfg.getAutoStart());
            map.put("runHistory", beanCfg.getRunHistory());
            map.put("description", beanCfg.getDescription());

            for (Map.Entry<Trigger, SchedulerFactoryBean> me : allTriggers.entrySet()) {
                Trigger trigger = me.getKey();
                if (trigger.getJobKey().getName().equals(beanCfg.getJobClass())) {
                    ExtSchedulerFactoryBean schedulerFactoryBean = (ExtSchedulerFactoryBean) me.getValue();
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    map.put("previousFireTime", cronTrigger.getPreviousFireTime());
                    map.put("nextFireTime", cronTrigger.getNextFireTime());
                    try {
                        map.put("stateLabel", scheduler.getTriggerState(trigger.getKey()));
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
