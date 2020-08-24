package com.yym.springboot.strategy.service;

public interface IPayService {
    /**
     * 通过渠道消费
     *
     * @param channel   渠道
     * @param amount    金额
     * @return s
     */
    String pay(String channel,String amount);
}
