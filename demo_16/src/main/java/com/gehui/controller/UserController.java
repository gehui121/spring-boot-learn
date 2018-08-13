package com.gehui.controller;

import com.gehui.entity.UserEntity;
import com.gehui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/8/10 18:00.
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public List<UserEntity> list(){
        return userService.list();
    }
}
