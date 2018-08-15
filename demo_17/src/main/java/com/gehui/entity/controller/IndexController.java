package com.gehui.entity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/13 16:43.
 **/
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "访问到index成功";
    }
}
