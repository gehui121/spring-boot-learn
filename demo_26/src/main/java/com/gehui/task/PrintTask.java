package com.gehui.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/10/8 17:28.
 * @scheduled注解用来配置到方法上来完成对应的定时任务的配置
 **/
@Component
public class PrintTask {

    /**
     * 每隔5分钟调用一次
     */
    @Scheduled(cron = "0 0/5 * * * *")
    public void corn(){
        System.out.println("执行测试corn时间" + new Date(System.currentTimeMillis()));
    }

    /**
     * 是上一个调用开始后再次调用的延时（不用等待上一次调用完成）
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 1000*4)
    public void fixedRate() throws InterruptedException {
        Thread.sleep(1000*2);
        System.out.println("不用等待上一次调用完成的延时" + new Date(System.currentTimeMillis()));
    }

    /**
     * 该属性的功效与上面的fixedRate则是相反的，配置了该属性后会等到方法执行完成后延迟配置的时间再次执行该方法
     * @throws InterruptedException
     */
    @Scheduled(fixedDelay = 1000*1)
    public void fixedDelay() throws InterruptedException {
        Thread.sleep(1000*3);
        System.out.println("上次调用完成后的延时" + new Date(System.currentTimeMillis()));
    }

}
