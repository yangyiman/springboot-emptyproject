package com.yym.springboot.aop.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * bean依赖注入的方式:
 *  1. 使用注解,如@Componet
 *  2. 使用@Bean
 *  3. 使用工厂FactoryBean创建
 */
// 方式2: 使用@Bean,将User注入容器
@Configuration
public class BeanImport2 {

    @Bean
    public User getUser(){
        return new User();
    }
}

@Data
class User{
    private String name;
}
