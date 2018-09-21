package com.gehui;

import com.gehui.interceptor.JwtTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/9/13 16:04.
 * 创建配置类，将创建的拦截器添加到SpringBoot项目中
 * 我们配置JWT拦截器只拦截/api/下的所有路径
 **/
@Configuration
public class JWTConfiguration implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtTokenInterceptor()).addPathPatterns("/api/**");
    }
}
