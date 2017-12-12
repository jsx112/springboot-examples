package com.springboot.eureka.provider;/**
 * Created by dell on 2017/11/16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 *
 * @author jiasx
 * @create 2017-11-16 15:36
 **/
@EnableEurekaClient
@SpringBootApplication
public class EurekaProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApp.class, args);
    }
}
