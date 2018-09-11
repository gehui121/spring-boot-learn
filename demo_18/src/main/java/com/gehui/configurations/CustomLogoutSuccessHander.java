package com.gehui.configurations;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义登出控制
 * Created by Administrator on 2018/8/22 18:19.
 * 当我们退出系统时需要访问SpringSecrutiy的logout方法来清空对应的session信息，
 * 那我们退出后改用户的access_token还依然存在那就危险了，
 * 一旦别人知道该token就可以使用之前登录用户的权限来操作业务
 **/
@Component
public class CustomLogoutSuccessHander
        extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {

    private static final String BEARER_AUTHENTICATION = "bearer";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Resource
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        if (token != null && token.startsWith(BEARER_AUTHENTICATION)){
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[0]);
            if (oAuth2AccessToken != null){
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
