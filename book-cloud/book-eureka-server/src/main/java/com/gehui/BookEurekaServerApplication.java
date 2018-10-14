package com.gehui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Administrator on 2018/10/14 11:41.
 * 默认将自己注册到自己的容器中Connection refused: connect所以报错  连接被拒绝在配置文件中配置就可以
 **/
@SpringBootApplication
@EnableEurekaServer//启用eureka服务
public class BookEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookEurekaServerApplication.class);
    }

}
