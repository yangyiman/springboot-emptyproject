package com.yym.springboot.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.yym.springboot.autoconfig","com.yym.springboot.swagger2"})
public class Swagger2Application {
    public static void main(String[] args) {
        SpringApplication.run(Swagger2Application.class,args);
    }
}
