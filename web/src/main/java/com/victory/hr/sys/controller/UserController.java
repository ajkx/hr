package com.victory.hr.sys.controller;

import com.victory.hr.base.BaseCURDController;
import com.victory.hr.po.UserPO;
import com.victory.hr.sys.entity.Role;
import com.victory.hr.sys.entity.User;
import com.victory.hr.sys.service.PasswordHelper;
import com.victory.hr.sys.service.RoleService;
import com.victory.hr.sys.service.UserService;
import com.victory.hr.vo.JsonVo;
import com.victory.hr.vo.PasswordVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/5/9.
 * Time:16:46
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseCURDController<User, Integer> {


    @Autowired
    public PasswordHelper passwordHelper;

    @Autowired
    private RoleService roleService;

    private UserService getUserService() {
        return (UserService) baseService;
    }

    public UserController() {
        setResourceIdentity("user");
    }

    @Override
    protected void setCommonData(Model model) {
    }


    @RequestMapping(value = "/test")
    public
    @ResponseBody
    JsonVo test(@RequestBody UserPO userPO) {
        getUserService().save(fillUserRole(userPO));
        return null;
    }

    @RequestMapping(value = "/changePassword")
    public @ResponseBody
    JsonVo changePassword(PasswordVo passwordVo, HttpSession session){
        //先判断用户
        String username = (String) session.getAttribute("username");
        User user = getUserService().findByUserName(username);
        String currentPassword = passwordVo.getCurrentPassword();
        String newPassword = passwordVo.getPassword();
        String confirmPassword = passwordVo.getConfirmPassword();
        String salt ="";
        JsonVo jsonVo = new JsonVo();
        if (user != null) {
            salt = user.getCredentialsSalt();
            if(!passwordHelper.encryptPassword(currentPassword,salt).equals(user.getPassword())){
                jsonVo.setStatus(false);
                jsonVo.setMsg("原密码错误！");
            }else{
                if(!passwordHelper.encryptPassword(newPassword,salt).equals(user.getPassword())){
                    user.setPassword(passwordHelper.encryptPassword(newPassword,salt));
                    getUserService().update(user);
                    jsonVo.setStatus(true);
                    jsonVo.setMsg("修改完成！");
                }
            }

        }else{
            jsonVo.setStatus(false);
            jsonVo.setMsg("会话过期！");
        }
        return jsonVo;
    }


    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "/jsonlist")
    public @ResponseBody
    List jsonList(){
        List<User> temp = getUserService().findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (User user : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", user.getId() + "");
            map.put("name", user.getName());
            list.add(map);
        }
        return list;
    }

    public User fillUserRole(UserPO userPo) {
        User user = userPo.getUser();
        user.setRoles(userPo.getRoles());
//        int[] roleids = userPo.getRoles();;
//        for(int i = 0; i < roleids.length; i++) {
//            Role role = roleService.findOne(roleids[i]);
//            if (role != null) {
//                user.getRoles().add(role);
//            }
//        }
        return user;
    }
}
