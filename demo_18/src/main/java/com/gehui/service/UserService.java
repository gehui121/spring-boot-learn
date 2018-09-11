package com.gehui.service;

import com.gehui.entity.Authority;
import com.gehui.entity.User;
import com.gehui.jpa.UserJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2018/8/22 12:41.
 **/
@Component("userService")
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserJPA userJPA;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String lowerCaseUserName = username.toLowerCase();
        User userFromDataBase = userJPA.findByUsernameCaseInsensitive(lowerCaseUserName);
        logger.info("数据库查到的用户是;" + userFromDataBase);
        if (userFromDataBase == null){
            throw new UsernameNotFoundException("user"+lowerCaseUserName+"was not found in the database");
        }
        //获取用户所有的权限，并且SpringSecurity需要的集合
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDataBase.getAuthority()){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        logger.info("权限集合",grantedAuthorities.toString());
        //返回一个SpringSecurity需要的用户对象
        return new org.springframework.security.core.userdetails.User(
                userFromDataBase.getUsername(),
                userFromDataBase.getPassword(),
                grantedAuthorities
        );
    }
}
