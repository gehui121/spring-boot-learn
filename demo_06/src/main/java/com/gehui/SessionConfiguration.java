package com.gehui;

import com.gehui.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/7/25 13:03.
 * 将SessionInterceptor拦截器添加到SpringBoot的配置中，
 **/
@Configuration
public class SessionConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //配置拦截路径，对以下路径进行过滤
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
    }
}
