package com.gehui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/7/25 12:45.
 * 初始化登录页面，首页页面的转发配置，
 **/
@Controller
@RequestMapping(value = "/user")
public class IndexController {
    /**
     * 初始化登录页面
     */
    @RequestMapping(value = "/login_view", method = RequestMethod.GET)
    public String login_view() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
