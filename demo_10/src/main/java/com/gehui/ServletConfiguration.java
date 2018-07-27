package com.gehui;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/7/27 13:24.
 * 使用Bean注册Servlet
 * 将TestServlet通过ServletRegistrationBean让SpringBoot项目知道它的存在，并且配置的请求路径为/test
 *
 * 本章主要讲解了SpringBoot如何创建Servlet以及使用两种方式装配到项目中，一种是手动装配的方式、而另外一种是自动装配。
 **/
@Configuration
@ServletComponentScan
public class ServletConfiguration {

   /* @Bean
    public ServletRegistrationBean servletRegistrationBean()
    {
        return new ServletRegistrationBean(new TestServlet(),"/test");
    }*/
}
