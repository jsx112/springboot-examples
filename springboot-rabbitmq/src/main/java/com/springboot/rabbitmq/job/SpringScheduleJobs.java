package com.springboot.rabbitmq;

import com.springboot.rabbitmq.service.BusinessSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 定时任务公共能处理类，如果需要新的定时任务，可以增加方法和时间。
 */
@Component
public class SpringScheduleJobs {

    private SimpleDateFormat fdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Autowired
    private BusinessSenderService businessSenderService;

//    /**
//     *
//     * 固定等待时间 @Scheduled(fixedDelay = 时间间隔 )
//     */
//    @Scheduled(fixedDelay=SECOND)
//    public void fixedDelay() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("[FixedDelayJob Execute]"+fdf.format(new Date()));
//    }
//
//    /**
//     *
//     * 固定间隔时间 @Scheduled(fixedRate = 时间间隔 )
//     */
//    @Scheduled(fixedRate=SECOND)
//    public void fixedRate(){
//        System.out.println("[FixedRateJob Execute]"+fdf.format(new Date()));
//    }
//
    /**
     *
     * Corn表达式 @Scheduled(cron = Corn表达式)
     */
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void cron(){
//        sender.sendMessage(UUID.randomUUID().toString(),"测试消息回调："+fdf.format(new Date())+",消息内容：定时任务生成");
//    }
}