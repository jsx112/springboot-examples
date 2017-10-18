package com.springboot.rabbitmq.controller;

import com.springboot.rabbitmq.domain.BusinessOrder;
import com.springboot.rabbitmq.service.BusinessSenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Api(tags = {"Rabbitmq消息发送服务"},description="Rabbitmq消息发送服务,测试string发送，对象发送等")
public class RabbitmqController {

    @Autowired
    private BusinessSenderService businessSenderService;
  
    @RequestMapping("/sendstr")
    @ApiOperation(value="发送字符串消息", notes="发送字符串消息",httpMethod = "POST", response = String.class)
    public String sendStr(String msg) {
        businessSenderService.sendMessage(UUID.randomUUID().toString(),msg);
        return "Sendstr OK.";
    }  

    @RequestMapping("/sendobj")
    @ApiOperation(value="发送对象消息", notes="发送对象消息",httpMethod = "POST", response = String.class)
    public String sendObject(@RequestBody BusinessOrder businessOrder) {
        businessSenderService.sendMessage(businessOrder.getBussinessCode()+":"+businessOrder.getId(),businessOrder);
        return "Sendobj OK.";
    }
}