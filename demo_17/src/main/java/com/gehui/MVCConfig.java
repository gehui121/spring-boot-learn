package com.gehui;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/8/13 16:37.
 * 配置一个简单的SpringBoot内的MVC控制器跳转，添加一个名叫MVCConfig配置类实现WebMvcConfigurer接口，
 * 重写addViewControllers()方法添加路径访问，可以通过Get形式的/login访问到我们的login.jsp，
 **/
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main").setViewName("main");
    }
}
