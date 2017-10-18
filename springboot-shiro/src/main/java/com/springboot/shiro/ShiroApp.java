package com.springboot.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
* app运行入口类
* @author jiasx
* @create 2017/10/18 17:04
**/
@SpringBootApplication
@ServletComponentScan
public class ShiroApp {
    public static void main(String[] args) {
       SpringApplication.run(ShiroApp.class, args);
    }
}