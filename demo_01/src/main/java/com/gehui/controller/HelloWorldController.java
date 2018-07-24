package com.gehui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/24 12:15.
 **/
@RestController
public class HelloWorldController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "HelloWorld:葛辉";
    }
}
