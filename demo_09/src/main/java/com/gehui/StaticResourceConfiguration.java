package com.gehui;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/7/26 21:55.
 * 我们配置了静态资源的路径为/resources/**，
 * 那么只要访问地址前缀是/resources/，就会被自动转到项目根目录下的static文件夹内。
 * 我们访问：127.0.0.1:8080/resources/t.png就会被解析成127.0.0.1:8080/t.png。
 *
 **/
@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
    }
}
