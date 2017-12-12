package com.springboot.xxljob;/**
 * Created by dell on 2017/9/15.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

/**
 * 定时任务启动类
 *
 * @author jiasx
 * @create 2017-09-15 11:03
 **/
@SpringBootApplication
public class SpringbootXxljobApp {
    public static void main(String[] args) {

        long curTime = System.currentTimeMillis()/(24*3600*1000);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);

        long time1 = calendar.getTimeInMillis()/(24*3600*1000);

        System.out.println(curTime+"===="+time1+"==="+(curTime-time1));
//        SpringApplication.run(SpringbootXxljobApp.class,args);

    }
}
