package com.springboot.rabbitmq.config;/**
 * Created by dell on 2017/10/16.
 */

import com.springboot.rabbitmq.consume.MqConsumerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Rabbitmq的配置
 *
 * @author jiasx
 * @create 2017-10-16 15:20
 **/
@Configuration
public class RabbitmqConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Value("${spring.rabbitmq.queuename}")
    private String queueName;
    @Value("${spring.rabbitmq.exchange}")
    private String queueExchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;



    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.rabbitmq")
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setExchange(queueExchange);
        template.setRoutingKey(routingkey);
        template.setMessageConverter(converter);
        return template;
    }


    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }


    /**
     * 创建exchange, 可以创建TopicExchange(*、#模糊匹配routing key，routing key必须包含".")，DirectExchange，FanoutExchange(无routing key概念)
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        /**
         * DirectExchange:按照routingkey分发到指定队列
         * TopicExchange:多关键字匹配
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * HeadersExchange ：通过添加属性key-value匹配
         */
        return new TopicExchange(queueExchange);
    }

    /**
     * 将队列绑定到交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }



    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Queue queue, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setPrefetchCount(1000);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(MqConsumerListener listener, MessageConverter converter) {
        return new MessageListenerAdapter(listener, converter);
    }

}
