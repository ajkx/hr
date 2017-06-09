package com.victory.hr.schedule.controller;

import com.victory.hr.attendance.entity.AttendanceDetail;
import com.victory.hr.base.BaseCURDController;
import com.victory.hr.schedule.ExtSchedulerFactoryBean;
import com.victory.hr.schedule.entity.JobBeanCfg;
import com.victory.hr.schedule.service.JobBeanCfgService;
import com.victory.hr.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/6/3.
 * Time:11:54
 */
@Controller
@RequestMapping(value = "/scheduler/job")
public class JobBeanCfgController extends BaseCURDController<JobBeanCfg,Integer>{

    private JobBeanCfgService getService() {
        return (JobBeanCfgService) baseService;
    }

    public JobBeanCfgController() {
        setResourceIdentity("Scheduler");
    }

    @Override
    protected void setCommonData(Model model, JobBeanCfg entity) {
    }

    @RequiresPermissions("Scheduler:control")
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    @ResponseBody
    public JsonVo doStateTrigger(int id, String state) throws SchedulerException {
        JobBeanCfg jobBeanCfg = getService().findOne(id);
        JsonVo jsonVo = new JsonVo();
        if (jobBeanCfg == null) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("找不到该任务");
        }else{
            Map<Trigger, SchedulerFactoryBean> allTriggers = getService().findAllTriggers();
            boolean exist = false;
            for (Map.Entry<Trigger, SchedulerFactoryBean> me : allTriggers.entrySet()) {
                Trigger trigger = me.getKey();
                if (trigger.getJobKey().getName().equals(jobBeanCfg.getJobClass())) {
                    exist = true;
                    if (state.equals("pause")) {
                        me.getValue().getScheduler().pauseTrigger(trigger.getKey());
                    } else if (state.equals("resume")) {
                        me.getValue().getScheduler().resumeTrigger(trigger.getKey());
                    }else{
                        throw new UnsupportedOperationException("state parameter [" + state + "] not in [pause, resume]");
                    }
                    break;
                }
            }
            if(exist){
                jsonVo.setStatus(true);
                jsonVo.setMsg("操作成功");
            }else{
                jsonVo.setStatus(false);
                jsonVo.setMsg("没找到对应trigger");
            }
        }
        return jsonVo;
    }

    @RequiresPermissions("Scheduler:control")
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    public JsonVo doRunTrigger(int id) throws SchedulerException {
        JobBeanCfg jobBeanCfg = getService().findOne(id);
        JsonVo jsonVo = new JsonVo();
        if (jobBeanCfg == null) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("找不到该任务");
        }else{
            Map<Trigger, SchedulerFactoryBean> allTriggers = getService().findAllTriggers();
            boolean exist = false;
            for (Map.Entry<Trigger, SchedulerFactoryBean> me : allTriggers.entrySet()) {
                Trigger trigger = me.getKey();
                if (trigger.getJobKey().getName().equals(jobBeanCfg.getJobClass())) {
                    exist = true;
                    ExtSchedulerFactoryBean schedulerFactoryBean = (ExtSchedulerFactoryBean) me.getValue();
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();
                    if (!scheduler.isStarted()) {
                        scheduler.start();
                    }
                    scheduler.triggerJob(trigger.getJobKey());
                    break;
                }
            }
            if(exist){
                jsonVo.setStatus(true);
                jsonVo.setMsg("执行成功");
            }else{
                jsonVo.setStatus(false);
                jsonVo.setMsg("没找到对应trigger");
            }
        }
        return jsonVo;
    }
}
