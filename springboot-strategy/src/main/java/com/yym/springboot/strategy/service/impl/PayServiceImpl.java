package com.yym.springboot.strategy.service.impl;

import com.yym.springboot.strategy.eenum.PayEnum;
import com.yym.springboot.strategy.util.PayStrategyFactory;
import com.yym.springboot.strategy.service.IPayService;
import com.yym.springboot.strategy.service.IPayStrategy;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements IPayService {
    private static String MSG = "使用 %s ,消费了 %s 元";

    @Override
    public String pay(String channel, String amount) {
        if (PayEnum.ALI_PAY.getChannel().equals(channel)) {
            return String.format(MSG, PayEnum.ALI_PAY.getDescription(), amount);
        } else if (PayEnum.WX_PAY.getChannel().equals(channel)) {
            return String.format(MSG, PayEnum.WX_PAY.getDescription(), amount);
        }
        throw new RuntimeException("未知支付方式");
    }
}
