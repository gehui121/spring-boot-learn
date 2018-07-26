package com.gehui;

import com.gehui.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/7/26 11:29.
 * 将LoggerInterceptor拦截器加载到SpringBoot配置中
 **/
@Configuration
public class LoggerConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //配置拦截路径，对以下路径进行拦截
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
}
