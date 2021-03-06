package com.victory.hr.base;

import com.victory.hr.attendance.enums.Status;
import com.victory.hr.common.service.BaseService;
import com.victory.hr.common.Constants;
import com.victory.hr.common.controller.BaseController;
import com.victory.hr.common.controller.PermissionList;
import com.victory.hr.common.entity.AbstractEntity;
import com.victory.hr.common.search.PageInfo;
import com.victory.hr.common.search.Pageable;
import com.victory.hr.common.search.SearchFilter;
import com.victory.hr.common.search.Searchable;
import com.victory.hr.common.utils.DateUtils;
import com.victory.hr.hrm.entity.HrmDepartment;
import com.victory.hr.hrm.entity.HrmResource;
import com.victory.hr.hrm.entity.HrmStatus;
import com.victory.hr.hrm.entity.HrmSubCompany;
import com.victory.hr.hrm.service.HrmDepartmentService;
import com.victory.hr.hrm.service.HrmResourceService;
import com.victory.hr.hrm.service.HrmSubCompanyService;
import com.victory.hr.vo.JsonVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/5/8.
 * Time:19:39
 */
public abstract class BaseCURDController<T extends AbstractEntity, ID extends Serializable> extends BaseController<T, ID> {

    @Autowired
    protected BaseService<T, ID> baseService;

    //    当前Controller的权限字符串组
    protected PermissionList permissionList = null;

    @Autowired
    private HrmResourceService resourceService;



    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }

    protected void setIndexData(Model model) {

    }
    /**
     * 返回模块的首页
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        //判断是否有view权限
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }
        setIndexData(model);
        return viewName("index");
    }

    /**
     * 返回json列表数据
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo list(HttpServletRequest request, Model model) {
        PageInfo info = baseService.findAll(getSearchable(request));
        return info;
    }

    //    id自动转实体
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") ID id) {
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }
        T entity = baseService.findOne(id);
        setCommonData(model,entity);
        model.addAttribute("t", entity);
        model.addAttribute(Constants.OP_NAME, "查看");
        return viewName("view");
    }

    //返回创建表单
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }
        setCommonData(model,null);
        model.addAttribute(Constants.OP_NAME, "新增");
        return viewName("editForm");
    }

    //执行创建操作
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonVo create(@RequestBody @Valid T t, BindingResult result) {
        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        JsonVo jsonVo = new JsonVo();
        if (hasError(t, result)) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("验证失败！");
            return jsonVo;
        }
        baseService.save(t);
        jsonVo.setStatus(true);
        jsonVo.setMsg("创建成功！");
        return jsonVo;
    }

    //返回更新表单
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") ID id, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }
        T entity = baseService.findOne(id);
        setCommonData(model,entity);
        model.addAttribute("t", entity);
        model.addAttribute(Constants.OP_NAME, "修改");



        return viewName("editForm");
    }

    //执行更新操作
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody JsonVo update(@RequestBody @Valid T t, BindingResult result) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        JsonVo jsonVo = new JsonVo();
        if (hasError(t, result)) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("验证失败！");
            return jsonVo;
        }
        baseService.update(t);
        jsonVo.setStatus(true);
        jsonVo.setMsg("更新成功！");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.POST)
    public @ResponseBody JsonVo delete(@PathVariable("id") ID id) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }
        T entity = baseService.findOne(id);
        baseService.delete(entity);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功!");
        return jsonVo;
    }

    public Searchable getSearchable(HttpServletRequest request) {
        String path = request.getServletPath();
        String beginDateStr = "";
        String endDateStr = "";
        String resources = "";

        Searchable searchable = new Searchable();
        searchable.setRequest(request);
        Pageable pageable = new Pageable();
        List<SearchFilter> searchFilters = new ArrayList<>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            switch (name) {
                case "cPage":
                    pageable.setcPage(Integer.parseInt(request.getParameter(name)));
                    break;
                case "pSize":
                    pageable.setpSize(Integer.parseInt(request.getParameter(name)));
                    break;
                case "name": {
                    SearchFilter searchFilter = new SearchFilter();
                    searchFilter.setKey(name);
                    searchFilter.setValue(request.getParameter(name));
                    searchFilter.setOperator("ilike");
                    searchFilter.setObjects(new Object[]{searchFilter.getKey(), searchFilter.getValue(), MatchMode.ANYWHERE});
                    searchFilter.setClazz(new Class[]{String.class, String.class, MatchMode.class});
                    searchFilters.add(searchFilter);
                    break;
                }
                case "beginDate": {
                    beginDateStr = request.getParameter("beginDate");
                    endDateStr = request.getParameter("endDate");

                    break;
                }
                case "resources": {
                    resources = request.getParameter(name);
                    break;
                }
                case "order":
                    String order = request.getParameter(name);

                    if ("desc".equals(order)) {
                        pageable.setOrder(Order.desc("id"));
                    } else if ("asc".equals(order)) {
                        pageable.setOrder(Order.asc("id"));
                    }
                    break;
                case "hrmstatus":{
                    String statusStr = request.getParameter(name);
                    if(StringUtils.isNotBlank(statusStr)){
                        SearchFilter searchFilter = new SearchFilter();
                        searchFilter.setKey(name);
                        searchFilter.setOperator("in");
                        searchFilter.setClazz(new Class[]{String.class,Collection.class});
                        String[] array = statusStr.split(",");
                        List<HrmStatus> statusList = new ArrayList<>();
                        for(int i = 0; i < array.length; i++) {
                            if("".equals(array[i])) continue;
                            int num = Integer.parseInt(array[i]);
                            HrmStatus status = HrmStatus.values()[num];
                            statusList.add(status);
                        }
                        searchFilter.setValue(statusList);
                        searchFilter.setObjects(new Object[]{searchFilter.getKey(),searchFilter.getValue()});
                        searchFilters.add(searchFilter);
                    }
                    break;
                }
                case "status":
                    String str = request.getParameter(name);
                    if(StringUtils.isNotBlank(str)) {
                        SearchFilter searchFilter = new SearchFilter();
                        searchFilter.setKey(name);
                        searchFilter.setOperator("eq");
                        searchFilter.setClazz(new Class[]{String.class, Object.class});
                        Status status = Status.valueOf(str);
                        searchFilter.setValue(status);
                        searchFilter.setObjects(new Object[]{searchFilter.getKey(), searchFilter.getValue()});
                        searchFilters.add(searchFilter);
                    }
                    break;
            }
        }
        //特殊判断是考勤信息的，即有日期和人员
        if (path.contains("attendance") && (path.contains("record") || path.contains("detail"))) {

            //日期信息的判断
            Date beginDate = null;
            Date endDate = null;
            if (StringUtils.isBlank(beginDateStr) || StringUtils.isBlank(endDateStr)) {
                beginDate = DateUtils.getMonthFristDay();
                endDate = DateUtils.getToday();
            } else {
                beginDate = DateUtils.parseDateByDay(beginDateStr);
                endDate = DateUtils.parseDateByDay(endDateStr);
            }
            SearchFilter searchFilter = new SearchFilter();
            searchFilter.setKey("date");
            searchFilter.setOperator("between");
            searchFilter.setObjects(new Object[]{searchFilter.getKey(), beginDate, endDate});
            searchFilter.setClazz(new Class[]{String.class, Object.class, Object.class});
            searchFilters.add(searchFilter);

            //人员信息的判断
            if (StringUtils.isNotBlank(resources)) {
                Set<HrmResource> resourceSet = resourceService.splitForHrmResource(resources);
                SearchFilter searchFilter1 = new SearchFilter();
                searchFilter1.setKey("resource");
                searchFilter1.setOperator("in");
                searchFilter1.setObjects(new Object[]{"resource",resourceSet});
                searchFilter1.setClazz(new Class[]{String.class,Collection.class});
                searchFilters.add(searchFilter1);
            }
        }
        searchable.setPageable(pageable);
        searchable.setSearchFilters(searchFilters);
        return searchable;
    }
}

