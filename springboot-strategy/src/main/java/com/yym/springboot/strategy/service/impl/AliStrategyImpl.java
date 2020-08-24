package com.yym.springboot.strategy.service.impl;

import com.yym.springboot.strategy.eenum.PayStrategyEnum;
import com.yym.springboot.strategy.service.IPayStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("ali")
public class AliStrategyImpl implements IPayStrategy {
    @Override
    public String pay(String channel, String amount) {
        return String.format(MSG, PayStrategyEnum.ALI_PAY.getDescription(),amount);
    }
}
