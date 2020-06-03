package com.yym.springboot.rabbitmq.config;

import com.yym.springboot.rabbitmq.direct.ManulDirectConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者端消息确认配置,暂时使用direct消息确认做例子
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private ManulDirectConsumer manulDirectConsumer;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // 设置手动确认消息机制,默认为自动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(manulDirectConsumer);
        container.setQueueNames("direct-queue");
        return container;
    }

}
