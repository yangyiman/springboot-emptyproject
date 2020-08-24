package com.yym.springboot.strategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 此案例是使用策略模式消除if-else的实践
 * 以支付方式为例:
 *  1. 传统的if-else模式
 *  2. 使用策略模式
 */
@SpringBootApplication
public class StrategyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrategyApplication.class, args);
    }
}
