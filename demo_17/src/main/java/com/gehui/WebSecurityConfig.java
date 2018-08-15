package com.gehui;

import com.gehui.entity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Created by Administrator on 2018/8/14 9:35.
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //完成自定义认证实体的注入
    @Bean
    UserDetailsService userService() {
        return new UserService();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()//所有请求必须登录后访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()//登录页面，错误页面可以直接访问
                .and()
                .logout()
                .permitAll();//注销请求可以直接访问
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
