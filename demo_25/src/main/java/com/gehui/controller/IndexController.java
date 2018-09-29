package com.gehui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/9/29 11:10.
 **/
@RestController
public class IndexController {
    @RequestMapping(value = "/cors")
    public String index(){
        return "This is cors info!";
    }
}
