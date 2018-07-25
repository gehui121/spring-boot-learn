package com.gehui.controller;

import com.gehui.entity.UserEntity;
import com.gehui.jpa.UserJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Administrator on 2018/7/25 10:00.
 **/
@RestController
@RequestMapping(value = "/user")
public class LoginController {
    @Autowired
    private UserJPA userJPA;

   private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(UserEntity user, HttpServletRequest request){
        if (user.getName() == null){
            logger.info("用户名为空");
//            throw new RuntimeException("用户名不能为空");
        }
        if (user.getPassword() == null){
            logger.info("密码为空");
//            throw new RuntimeException("密码不能为空");
        }
        logger.info("页面登录的用户是 ："+user.toString());
        //登录成功
        boolean flag = true;
       String result = "登录成功";
       //根据用户名查询用户是否存在
        Optional<UserEntity> userEntity = userJPA.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"),user.getName()));
                return null;
            }
        });
        /*
        Optional类是一个可以为null的容器对象，java8新特性，如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        Optional 类的引入很好的解决空指针异常。
        T get()如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException

         */
        logger.info("查询数据库的到的用户是 ："+ userEntity.toString());
        //判断如果userEntity存在值的情况下，获取密码进行比较，
        if (userEntity.isPresent()){
            if (!userEntity.get().getPassword().equals(user.getPassword())){
                //从数据库查询到的密码与前台传入用户的密码不一致
                result = "登录失败，用户密码不正确";
                flag = false;
            }
        }else {
            //从数据库查询之后返回一个空的Optional,用户名不存在
            result = "登录失败，用户名不存在";
            flag = false;
        }
        //如果登录成功，将用户信息设置到session中
        if(flag){
            request.getSession().setAttribute("user",userEntity);
        }
        return result;
    }
}
