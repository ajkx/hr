package com.victory.hr.common.controller;

import com.victory.hr.common.entity.AbstractEntity;
import com.victory.hr.common.utils.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:11
 */
public abstract class BaseController<T extends AbstractEntity,ID extends Serializable> {

    /**
     * 实体类型
     */
    protected final Class<T> entityClass;

    private String viewPrefix;



    protected BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        setViewPrefix(defaultViewPrefix());
    }

    /**
     * 设置通用数据
     *
     * @param model
     */
    protected void setCommonData(Model model,T entity) {
    }

    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }

    /**
     * 共享的验证规则
     * 验证失败返回true
     *
     * @param t
     * @param result
     * @return
     */
    protected boolean hasError(T t, BindingResult result) {
        Assert.notNull(t,"实体为空");
        return result.hasErrors();
    }

}
