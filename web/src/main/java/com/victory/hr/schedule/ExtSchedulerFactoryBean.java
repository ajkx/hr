package com.victory.hr.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.victory.hr.schedule.entity.JobBeanCfg;
import com.victory.hr.schedule.service.JobBeanCfgService;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/6/2.
 * Time:16:19
 */
public class ExtSchedulerFactoryBean extends SchedulerFactoryBean{

    private static Logger logger = LoggerFactory.getLogger(ExtSchedulerFactoryBean.class);

    private ConfigurableApplicationContext applicationContext;

    private JobBeanCfgService jobBeanCfgService;

    public static Map<String, Boolean> TRIGGER_HIST_MAPPING = Maps.newHashMap();

    public JobBeanCfgService getJobBeanCfgService() {
        return jobBeanCfgService;
    }

    public void setJobBeanCfgService(JobBeanCfgService jobBeanCfgService) {
        this.jobBeanCfgService = jobBeanCfgService;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
        super.setApplicationContext(applicationContext);
    }

    //启动应用注册数据库里所有的计划任务
    @Override
    protected void registerJobsAndTriggers() throws SchedulerException {
        logger.debug("Invoking registerJobsAndTriggers...");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        List<JobBeanCfg> jobBeanCfgs = jobBeanCfgService.findAll();
        List<Trigger> allTriggers = Lists.newArrayList();

        List<Trigger> triggers = null;

        try {
            //读取在先在代码设置好的计划任务
            triggers = (List<Trigger>) FieldUtils.readField(this, "triggers", true);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }

        if (triggers == null) {
            triggers = Lists.newArrayList();
        } else {
            allTriggers.addAll(triggers);
        }

        for (JobBeanCfg jobBeanCfg : jobBeanCfgs) {

            Class<?> jobClass = null;
            try {
                jobClass = Class.forName(jobBeanCfg.getJobClass());
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
            if (jobClass == null) {
                continue;
            }
            String jobName = jobClass.getName();
            boolean jobExists = false;
            for (Trigger trigger : triggers) {
                if (trigger.getJobKey().getName().equals(jobName)) {
                    jobExists = true;
                    break;
                }
            }

            if (jobExists) {
                logger.warn("WARN: Skipped dynamic  job [{}] due to exists static configuration.", jobName);
                continue;
            }

            logger.debug("Build and schedule dynamical job： {}, CRON: {}", jobName, jobBeanCfg.getCronExpression());

            //Spring动态加载job bean
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(JobDetailFactoryBean.class);
            beanDefinitionBuilder.addPropertyValue("jobClass", jobBeanCfg.getJobClass());
            beanDefinitionBuilder.addPropertyValue("durability", true);
            beanFactory.registerBeanDefinition(jobName, beanDefinitionBuilder.getBeanDefinition());

            // Spring动态加载Trigger Bean
            String triggerName = jobName + ".Trigger";
            JobDetail jobDetail = (JobDetail) beanFactory.getBean(jobName);
            BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.rootBeanDefinition(CronTriggerFactoryBean.class);
            beanDefinitionBuilder1.addPropertyValue("jobDetail", jobDetail);
            beanDefinitionBuilder1.addPropertyValue("cronExpression", jobBeanCfg.getCronExpression());
            beanFactory.registerBeanDefinition(triggerName, beanDefinitionBuilder1.getBeanDefinition());

            allTriggers.add((Trigger) beanFactory.getBean(triggerName));
        }

        this.setTriggers(allTriggers.toArray(new Trigger[]{}));
        super.registerJobsAndTriggers();

        for (Trigger trigger : allTriggers) {
            TRIGGER_HIST_MAPPING.put(trigger.getJobKey().getName(), true);
            for (JobBeanCfg jobBeanCfg : jobBeanCfgs) {
                if (jobBeanCfg.getJobClass().equals(trigger.getJobKey().getName())) {
                    if (!jobBeanCfg.getAutoStart()) {
                        logger.debug("Setup trigger {} state to PAUSE", trigger.getKey().getName());
                        this.getScheduler().pauseTrigger(trigger.getKey());

                    }
                    TRIGGER_HIST_MAPPING.put(trigger.getJobKey().getName(), jobBeanCfg.getRunHistory());
                    break;
                }
            }
        }
    }

    public static boolean isTriggerLogRunHist(Trigger trigger) {
        Boolean hist = TRIGGER_HIST_MAPPING.get(trigger.getJobKey().getName());
        return hist == null ? true : hist;
    }
}
