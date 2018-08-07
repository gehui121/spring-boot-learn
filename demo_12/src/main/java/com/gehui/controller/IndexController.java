package com.gehui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/7 8:47.
 **/
@RestController
public class IndexController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        logger.info("日志 info级别");
        logger.debug("日志debug级别");
        logger.error("日志error级别");
        return "index";
    }
}
