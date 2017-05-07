package com.victory.hr.interceptor;

import com.victory.hr.sys.controller.Layout;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * pjax请求拦截器
 *
 * @author ajkx_Du
 * @create 2016-11-26 8:31
 */
public class PjaxInterceptor implements HandlerInterceptor {

    private static final String PJAX = "_pjax";
    private static final String DESC = "desc";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accept = request.getHeader("Accept");
        if (accept.contains("json")){
            return true;}
        String path = request.getServletPath();
        if (path.lastIndexOf(".html") == -1){
            return true;
        }

        String header = request.getHeader("X-PJAX");
        String desc = request.getHeader(DESC);
        //如果不为pjax请求，模态框请求，不为公用菜单请求，则进行layout的转发
        if (header == null && !path.contains(Layout.LAYOUT_PATH) && desc == null) {
            //因为没有servletpath 所以url直接等于layoutpath
            String url = request.getContextPath()+Layout.LAYOUT_PATH;
            request.getRequestDispatcher(url).forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
