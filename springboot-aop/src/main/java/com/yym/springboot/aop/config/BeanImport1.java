package com.yym.springboot.aop.config;

import org.springframework.stereotype.Component;

/**
 * bean依赖注入的方式:
 *  1. 使用注解,如@Componet
 *  2. 使用@Bean
 *  3. 使用工厂FactoryBean创建
 */
// 方式1: 使用注解,将自身注入容器
@Component
public class BeanImport1 {
    private String hi = "hi";

    public String toWho(String name){
        return hi + "name";
    }
}
