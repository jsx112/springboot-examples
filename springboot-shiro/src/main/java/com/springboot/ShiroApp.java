package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author Angel --守护天使
 * @version v.0.1
 * @date 2017年2月25日
 */
@SpringBootApplication
@ServletComponentScan
public class ShiroApp {
    public static void main(String[] args) {
       SpringApplication.run(ShiroApp.class, args);
    }
}