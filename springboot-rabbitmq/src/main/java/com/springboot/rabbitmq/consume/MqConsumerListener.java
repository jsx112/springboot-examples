package com.springboot.rabbitmq.consume;

import com.rabbitmq.client.Channel;
import com.springboot.rabbitmq.config.RabbitmqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
* 消息监听器/接收方
* @author jiasx
* @create 2017/10/17 14:52
**/
@Component
public class MqConsumerListener implements ChannelAwareMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Resource
    private MessageConverter messageConverter;
    @Resource
    private RabbitTemplate rabbitTemplate;
	@Value("${spring.rabbitmq.appid}")
	private String appId;

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
		LOGGER.info("----- received" + message.getMessageProperties());
		try {
			Object msg = messageConverter.fromMessage(message);
			if (!appId.equals(message.getMessageProperties().getAppId())){
		        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		        throw new SecurityException("非法应用appId:" + message.getMessageProperties().getAppId());
			}
			LOGGER.info("收到的消息："+msg);
			//处理消息

	        //确认消息成功消费
	        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (SecurityException | IllegalArgumentException e) {
			LOGGER.info("------ err"+ e.getMessage());
	        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		}
    }

}
