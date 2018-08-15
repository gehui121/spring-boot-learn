package com.gehui.entity.service;

import com.gehui.entity.UserEntity;
import com.gehui.entity.jpa.UserJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Administrator on 2018/8/13 16:01.
 * 实现SpringSecurity内的UserDetailsService接口来完成自定义查询用户的逻辑
 **/
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
     UserJPA userJPA;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        UserEntity user = userJPA.findByUsername(username);
        if (user == null){
            logger.info("未查询到"+ username + "的用户信息");
            throw new UsernameNotFoundException("未查询到"+username + "的用户信息");
        }
        logger.info("查到的用户信息是："+ user.toString());
        return user;
    }
}
