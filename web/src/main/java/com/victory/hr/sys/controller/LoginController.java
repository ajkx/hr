package com.victory.hr.sys.controller;

import com.victory.hr.sys.entity.User;
import com.victory.hr.sys.service.ResourceService;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.vo.JsonVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ajkx_Du
 * @create 2016-11-04 14:46
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    JsonVo login(User user, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
        JsonVo jsonVo = new JsonVo();
        try {
            subject.login(token);
            request.getSession().setAttribute("username",user.getName());
            jsonVo.setStatus(true).addValue("url","home.html");
            return jsonVo;
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            e.printStackTrace();
            jsonVo.setStatus(false).setMsg("用户名或密码错误");
            return jsonVo;
        } catch (LockedAccountException e) {
            e.printStackTrace();
            jsonVo.setStatus(false).setMsg("账号锁定，请联系系统管理员！");
            return jsonVo;
        } catch (Exception e) {
            e.printStackTrace();
            jsonVo.setStatus(false).setMsg("fail");
            return jsonVo;
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String test() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping(value = "/regist")
    public @ResponseBody
    JsonVo regist(HttpServletRequest request, @RequestParam(required = true)String regist_account, @RequestParam(required = true)String regist_password, @RequestParam(required = true)String password_confirm) {
        JsonVo jsonVo = new JsonVo();
        if(!regist_password.equals(password_confirm)){
            jsonVo.setStatus(false).setMsg("两次密码输入不一致");
        }else{
            if(userService.findByUserName(regist_account) != null){
                jsonVo.setStatus(false).setMsg("账号名已被注册！");
            }else{
                User user = new User();
                user.setName(regist_account);
                user.setPassword(regist_password);
                user = userService.createUser(user);
                jsonVo.setStatus(true).setMsg("注册成功！请登录使用").put("regist",true);
            }
        }
        return jsonVo;
    }

}
