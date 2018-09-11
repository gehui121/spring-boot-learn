package com.gehui.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 配置安全资源服务器
 * Created by Administrator on 2018/8/22 17:32.
 * 使用@EnableResourceServer注解来开启资源服务器，因为整合SpringSecurity的缘故，
 * 我们需要配置登出时清空对应的access_token控制以及自定义401错误内容（authenticationEntryPoint），
 * 在配置类中我们排除了对/hello公开地址拦截以及/secure下的所有地址都必须授权才可以访问。
 **/
@Configuration
public class OAuth2Configuration {

   @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

       @Autowired
       private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
       @Autowired
       private CustomLogoutSuccessHander customLogoutSuccessHander;


       @Override
       public void configure(HttpSecurity http) throws Exception {
           http
                   .exceptionHandling()
                   .authenticationEntryPoint(customAuthenticationEntryPoint)
                   .and()
                   .logout()
                   .logoutUrl("/oauth/logout")
                   .logoutSuccessHandler(customLogoutSuccessHander)
                   .and()
                   .authorizeRequests()
                   .antMatchers("/hello/").permitAll()
                   .antMatchers("/secure/**").authenticated();
       }
    }

    /**
     * 开启OAuth2验证服务器
     * 在OAuth2Configuration配置类中添加一个子类，用于开启OAuth2的验证服务器
     */
    @Configuration
    @EnableAuthorizationServer
    public static class AuthorizationServerConfiguration
            extends AuthorizationServerConfigurerAdapter
            implements EnvironmentAware{

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String PROP_CLIENTID  = "clientid";
        private static final String PROP_SECRET  = "secret";
        private static final String PROP_TOKEN_VALIDITY_SECONDS  = "tokenValidityInSeconds";

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore(){
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        //qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的
        public AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints){
            endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);

        }


        @Override
        public void setEnvironment(Environment environment) {

        }
    }

}