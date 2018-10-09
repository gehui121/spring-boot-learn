package com.gehui.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2018/9/29 17:03.
 * @Primary配置了数据源为主数据源，当没有配置自动切换的package时默认使用该数据源进行数据处理操作。
 **/
@Configuration
public class DataSourceConfigurer {
    //配置用户数据源
    @Bean(name = "userDataSoutce")//装配该方法返回值为userDataSource管理bean
    @Qualifier("userDataSoutce")//spring装配bean唯一标识,使用@Qualifier注解并且设置内容，是为了防止注入时冲突问题。
    @ConfigurationProperties(prefix = "spring.datasource.user")//application.yml中配置的数据源的前缀
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }
    //书籍数据源
    @Bean(name = "bookDataSource")
    @Qualifier("bookDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.book")
    public DataSource bookDataSource(){
        return DataSourceBuilder.create().build();
    }

}
