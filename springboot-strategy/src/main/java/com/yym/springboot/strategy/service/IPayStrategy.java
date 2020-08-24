package com.yym.springboot.strategy.service;

public interface IPayStrategy {
    String MSG = "使用 %s ,消费了 %s 元";
    String pay(String channel,String amount);
}
