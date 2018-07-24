package com.gehui.controller;

import com.gehui.entity.UserEntity;
import com.gehui.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24 13:21.
 **/
@RestController
@RequestMapping(value = "/user")
/**
 * SpringBoot使用SpringDataJPA完成CRUD
 */
public class UserController {
    @Autowired
    private UserJPA userJPA;

    /*
    查询所有的user
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    /*
    添加user，更新user当ID为空时就是增加，当ID非空时更新
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public UserEntity save(UserEntity userEntity){
        return userJPA.save(userEntity);
    }

    /**
     * 根据ID删除user
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<UserEntity> delete(Long id){
        userJPA.deleteById(id);
        return userJPA.findAll();
    }
}
