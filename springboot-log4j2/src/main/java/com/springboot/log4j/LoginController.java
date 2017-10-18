package com.springboot.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
   
    @RequestMapping("/login")
    public String login(String username,String password){
        logger.info("hello,Andy,username="+username+",password="+password); //打印错误堆栈信息
       return "hello,Andy,username="+username+",password="+password;
    }

    @RequestMapping("/color")
    public String color(){
       return "#121212";
    }
}