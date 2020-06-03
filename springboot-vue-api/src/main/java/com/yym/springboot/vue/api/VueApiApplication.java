package com.yym.springboot.vue.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yym.springboot.vue.api.mapper")
public class VueApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueApiApplication.class,args);
    }
}

