package com.gehui.controller;

import com.alibaba.fastjson.JSONObject;
import com.gehui.utils.LoggerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/7/26 14:30.
 **/
@RestController
@RequestMapping(value = "/index")
public class IndexController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public JSONObject login(HttpServletRequest request,String name){
        JSONObject obj = new JSONObject();
        obj.put("msg","用户 ：" + name + "登录成功");
        //将返回值写入到请求对象中
        request.setAttribute(LoggerUtils.LOGGER_RETURN,obj);
        return obj;
    }

}


