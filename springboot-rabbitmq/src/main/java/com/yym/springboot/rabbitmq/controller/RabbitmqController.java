package com.yym.springboot.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public final static String MSG = "发送消息成功 : ";

    /**
     * 模拟消息发送到server,但是找不到exchange
     *
     * 触发confirmCallback函数
     *
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：false
     * ConfirmCallback:     原因：channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'test-exchange' in vhost '/', class-id=60, method-id=40)
     * @return
     */
    @GetMapping("/direct")
    public String testAck(String msg){
        rabbitTemplate.convertAndSend("test-exchange","test",msg);
        return MSG;
    }

    @GetMapping("/direct/no-key")
    public String testAckExistExchange(String msg){
        rabbitTemplate.convertAndSend("direct-exchange","test",msg);
        return MSG;
    }

    @GetMapping("/direct/success")
    public String testAckExist(String msg){
        rabbitTemplate.convertAndSend("direct-exchange","direct",msg);
        return MSG + msg;
    }

    @GetMapping("/direct/test-direct-exchange")
    public String testAckExist1(String msg){
        rabbitTemplate.convertAndSend("test-direct-exchange","direct",msg);
        return MSG + msg;
    }

    @GetMapping("/direct/{msg}")
    public String directMsg(@PathVariable String msg){
        rabbitTemplate.convertAndSend("direct-exchange","direct",msg);
        return MSG + msg;
    }
}
