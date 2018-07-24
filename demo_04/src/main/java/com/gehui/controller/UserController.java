package com.gehui.controller;

import com.gehui.entity.UserEntity;
import com.gehui.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24 14:31.
 * 使用Druid作为SpringBoot项目数据源（添加监控）
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserJPA userJPA;

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    /**
     * 新增或者更新用户
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public UserEntity save(UserEntity userEntity){
      UserEntity user = userJPA.save(userEntity);
      return user;
    }

    /**
     * 删除用户,返回用户列表
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<UserEntity> delete(Long id){
        userJPA.deleteById(id);
        return userJPA.findAll();
    }

}
