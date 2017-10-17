package com.jsx;

import java.util.Date;

import com.jsx.job.StartUpJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


/**
* 启动时候执行的方法，一些需要在启动时候加载的内容可以在此进行处理
* @author jiasx
* @create 2017/9/23 18:35
**/
@Component
public class AppStartUpAction implements ApplicationListener<ContextRefreshedEvent>{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void onApplicationEvent(ContextRefreshedEvent event) {

        logger.info("系统已启动，启动时间：{}",new Date());
    }

}