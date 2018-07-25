package com.gehui.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/7/25 12:53.
 * 拦截器实体类，
 **/
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception{
        logger.info("请求中的url为 ：{}",request.getRequestURI());
        //登录不做拦截的url
        if (request.getRequestURI().equals("/user/login")
                || request.getRequestURI().equals("/user/login_view")
                ){
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("user");
        if (obj == null){
            response.sendRedirect("/user/login_view");
            return false;
        }
        return true;

    }
}
