package com.gehui.controller;

import com.gehui.entity.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/9/27 17:04.
 **/
@Controller
public class UserController {
    @RequestMapping(value = "/user/index")
    public String index(HttpServletRequest request, UserBean user){
        //将name属性在index.jsp页面显示
        request.setAttribute("name",user.getUsername());
        return "index";
    }
}
