package com.yym.springboot.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UploadApplication.class);
        ConfigurableApplicationContext context = springApplication.run(args);
        System.out.println("BeanDefinitionCount = " + context.getBeanDefinitionCount());
    }
}
