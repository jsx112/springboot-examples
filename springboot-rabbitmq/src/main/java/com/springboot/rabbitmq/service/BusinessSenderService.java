package com.springboot.rabbitmq.service;/**
 * Created by dell on 2017/10/16.
 */

/**
 * 消息发送方
 *
 * @author jiasx
 * @create 2017-10-16 15:57
 **/
public interface BusinessSenderService{

    /**
     *
     * @param correlationId  消息唯一id标识
     * @param message  消息内容对象
     * @return
     */
    void sendMessage(final String correlationId, Object message);

}
