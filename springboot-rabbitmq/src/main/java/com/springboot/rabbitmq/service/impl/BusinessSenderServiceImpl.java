package com.springboot.rabbitmq.service.impl;/**
 * Created by dell on 2017/10/17.
 */

import com.springboot.rabbitmq.service.BusinessSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 业务订单服务
 *
 * @author jiasx
 * @create 2017-10-17 16:38
 **/
@Service
public class BusinessSenderServiceImpl implements BusinessSenderService,RabbitTemplate.ConfirmCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSenderService.class);

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    @Value("${spring.rabbitmq.appid}")
    private String appId;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param correlationId  消息唯一id标识
     * @param obj  消息内容对象
     * @return
     */
    @Override
    public void sendMessage(final String correlationId, Object obj) {
        LOGGER.info("sendMessage [this.{}, correlationId: {}]", this.getClass(), correlationId);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setCorrelationKey(correlationId);
        rabbitTemplate.convertAndSend(routingkey, obj, (message)->{
            message.getMessageProperties().setAppId(appId);
            message.getMessageProperties().setTimestamp(new Date());
            message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
            message.getMessageProperties().setCorrelationIdString(correlationId);
            return message;
        }, new CorrelationData(correlationId));
    }

    /** 回调方法 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("消息消费确认: " + correlationData.getId()+":"+ack+":"+cause);
        if (ack) {
            LOGGER.info("消息发送成功");
        } else {
            LOGGER.info("消息发送失败:" + cause);
        }
    }
}
