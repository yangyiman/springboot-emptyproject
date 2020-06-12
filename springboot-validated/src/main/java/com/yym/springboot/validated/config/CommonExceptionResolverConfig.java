package com.yym.springboot.validated.config;

import com.yym.springboot.common.exception.ArgumentExceptionControllerAdvice;
import com.yym.springboot.common.exception.CommonExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class CommonExceptionResolverConfig {

    @Bean
    public HandlerExceptionResolver commonExceptionResolver(){
        return new CommonExceptionResolver();
    }

    @Bean
    public ArgumentExceptionControllerAdvice argumentExceptionControllerAdvice(){
        return new ArgumentExceptionControllerAdvice();
    }
}
