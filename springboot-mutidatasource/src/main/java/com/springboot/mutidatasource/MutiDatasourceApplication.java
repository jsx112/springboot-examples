package com.springboot.mutidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
* 服务启动类
* @author jiasx
* @create 2017/11/22 17:04
**/
@SpringBootApplication
public class MutiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutiDatasourceApplication.class);
    }

}
