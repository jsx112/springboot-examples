package com.springboot.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


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