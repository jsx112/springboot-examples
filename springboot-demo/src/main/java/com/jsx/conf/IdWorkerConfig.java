package com.jsx.conf;

import com.jsx.util.IdWorker;
import com.jsx.util.Utils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Quartz定时任务配置
* @author jiasx
* @create 2017/9/15 15:27
**/
@Configuration
@ConfigurationProperties(prefix="generate.id")
public class IdWorkerConfig {

    /**
     * 机器id标识
     **/
    private long workId;

    /**
     * 应用/数据中心id标识
     **/
    private long datacenterId;

    @Bean
    public IdWorker idWorker() {
        IdWorker idWorker = new IdWorker(workId,datacenterId);
        Utils.idWorker = idWorker;
        return idWorker;
    }

}
