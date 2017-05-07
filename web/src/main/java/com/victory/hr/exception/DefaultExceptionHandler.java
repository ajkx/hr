package com.victory.hr.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ajkx_Du
 * @create 2016-11-07 9:52
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", "aaa");
        model.setViewName("unauthorized");
        return model;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView processRuntimeException(Exception e) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", e);
        model.setViewName("exception");
        return model;
    }
}
