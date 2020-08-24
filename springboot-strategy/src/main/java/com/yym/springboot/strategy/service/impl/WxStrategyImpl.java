package com.yym.springboot.strategy.service.impl;

import com.yym.springboot.strategy.eenum.PayEnum;
import com.yym.springboot.strategy.eenum.PayStrategyEnum;
import com.yym.springboot.strategy.service.IPayStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("wx")
public class WxStrategyImpl implements IPayStrategy {
    @Override
    public String pay(String channel, String amount) {
        return String.format(MSG, PayStrategyEnum.WX_PAY.getDescription(),amount);
    }
}
