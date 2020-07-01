package com.yym.springboot.autoconfig;

import com.yym.springboot.autoconfig.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AutoConfig {
    @Bean
    public Cat cat(){
        return new Cat();
    }
}
