package com.jsx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);
   
    @RequestMapping("/hello1")
    public String login(){
        logger.info("hello,Andy"); //打印错误堆栈信息
       return "hello";
    }
}