package com.yym.springboot.vue.api.config;

import com.yym.springboot.common.exception.ArgumentExceptionControllerAdvice;
import com.yym.springboot.common.exception.CommonExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {

    /**
     * 参数校验的增强controller
     * @return
     */
    @Bean
    public ArgumentExceptionControllerAdvice argumentExceptionControllerAdvice() {
        return new ArgumentExceptionControllerAdvice();
    }

    /**
     * 异常配置
     *
     * @param
     */
    @Bean
    public CommonExceptionResolver commonExceptionResolver() {
        CommonExceptionResolver commonExceptionResolver = new CommonExceptionResolver();
        return commonExceptionResolver;
    }
}
