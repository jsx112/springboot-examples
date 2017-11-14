package com.springboot.mongodb.base.config;

import com.springboot.mongodb.base.util.IdUtil;
import com.springboot.mongodb.base.util.IdWorker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * 全局唯一id生成器
 *
 * @author ysh
 * @create 2017-09-22
 **/
@Configuration
@ConfigurationProperties(prefix="spring.idworker")
public class IdWorkerConfig {
    private Long workerId;
    private Long dataCenterId;

    //单例模式
    @Bean
    @Scope("singleton")
    public IdWorker getIdWorker() {
        IdWorker idWorker = new IdWorker(workerId, dataCenterId);
        //同步初始化ID生成器
        IdUtil.idWorker = idWorker;
        return idWorker;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(Long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }
}
