package com.yym.springboot.strategy.controller;

import com.yym.springboot.strategy.service.IPayStrategy;
import com.yym.springboot.strategy.util.PayStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrategyController {

    @GetMapping("/st/{channel}/{amount}")
    public String test(@PathVariable String channel,
                       @PathVariable String amount) {
        IPayStrategy payStrategy = PayStrategyFactory.getPayStrategyById(channel);
        if (payStrategy == null) {
            throw new RuntimeException("未知支付方式");
        }
        return payStrategy.pay(channel, amount);
    }
}
