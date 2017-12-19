package com.sprinboot.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
* 短链服务启动类
* @author jiasx
* @create 2017/11/22 17:04
**/
@SpringBootApplication
@EnableAsync
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class);
    }

}
