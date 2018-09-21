package com.gehui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/9/13 16:16.
 **/
@Controller
@RequestMapping(value = "/api")
public class IndexController {

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(){
        return "success";
    }
}
