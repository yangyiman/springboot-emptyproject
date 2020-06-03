package com.yym.springboot.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 设置手动确认机制
 */
@Component
@RabbitListener(queues = "direct-queue")
public class ManulDirectConsumer implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        System.out.println("body = " + new String(body));
        // 为true放回队列
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}
