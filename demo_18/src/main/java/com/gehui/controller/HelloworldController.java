package com.gehui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/22 12:34.
 * 只添加一个字符串的输出，这个控制器我们开放，让SpringSecurity不去管理，
 **/
@RestController
@RequestMapping("/hello")
public class HelloworldController {
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(){
        return "Hello World";
    }
}
