package com.yym.springboot.strategy.service.impl;

import com.yym.springboot.strategy.service.IPayStrategy;
import org.springframework.stereotype.Service;

@Service("union")
public class UnionStrategyImpl implements IPayStrategy {
    @Override
    public String pay(String channel, String amount) {
        return String.format(MSG, "银联支付", amount);
    }
}
