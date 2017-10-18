package com.springboot.shiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 登录
* @author jiasx
* @create 2017/10/18 16:19
**/
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
   
    @RequestMapping("/login")
    public String login(String username,String password){
       return "登录信息为：username="+username+",password="+password;
    }

}