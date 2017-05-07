package com.victory.hr.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 *
 * @author ajkx_Du
 * @create 2016-11-26 11:47
 */
@Controller
public class Home {
    @RequestMapping(value = "/home.html")
    public String home(Model model) {
        return "home";
    }
}
