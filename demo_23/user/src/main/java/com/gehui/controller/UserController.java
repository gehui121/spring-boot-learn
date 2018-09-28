package com.gehui.controller;

import com.gehui.bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/9/28 14:35.
 **/
@Controller
public class UserController {
    @RequestMapping(value = "/user/index")
    public String index(HttpServletRequest request, UserBean userBean){
        //将username在index页面显示
        request.setAttribute("username",userBean.getUsername());
        return "index";
    }
}
