package com.gehui.controller;

import com.gehui.entity.UserEntity;
import com.gehui.jpa.UserJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/7 13:53.
 **/
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserEntity> list(){
        logger.info("info查询用户列表");
        logger.debug("debug查询用户列表");
        logger.error("error查询用户列表");
        return userJPA.findAll();
    }

    /**
     * 增加用户
     */
    @RequestMapping(value = "/add")
    public String add(){
        UserEntity userEntity = new UserEntity();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMDD");
        String birthday = format.format(date);
        userEntity.setBirthday(birthday);
        userEntity.setName("李佳琪");
        userEntity.setPassword("123456");
        userJPA.save(userEntity);
        logger.info("保存用户成功");
        return "保存成功";
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(Long userId){
        logger.info("要删除用户的ID是："+userId);
        userJPA.deleteById(userId);
        return "删除成功";
    }

    /**
     * 使用原生SQL查询年龄大于20的用户
     * @return
     */
    @RequestMapping(value = "/age")
    public List<UserEntity> age(){
        return userJPA.nativeQuery(20);
    }

    @RequestMapping(value = "/deleteById")
    public String deleteById(){
        userJPA.delete("李佳琪","123456");
        return "自定义sql删除成功";
    }

    /**
     * 分页查询
     * @return
     * page:传入页码默认从1开始
     */
    @RequestMapping(value = "/listByPage")
    public List<UserEntity> listByPage(int page){
        UserEntity userEntity = new UserEntity();
        //设置当前页和每页显示的条数
        userEntity.setPage(page);
        userEntity.setSize(3);
        //获取排序对象  这里的排序字段不是数据库内的字段名而是实体内的属性名, 获取实体类中默认排序进行三元运算符，最后定义排序方式
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(userEntity.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = new Sort(sort_direction,userEntity.getSidx());
        //创建分页对象
        PageRequest pageRequest = new PageRequest(userEntity.getPage()-1,userEntity.getSize(),sort);
        //执行分页查询
        Page<UserEntity> pageUser = userJPA.findAll(pageRequest);
        List<UserEntity> list_user = pageUser.getContent();
        return list_user;
    }
}
