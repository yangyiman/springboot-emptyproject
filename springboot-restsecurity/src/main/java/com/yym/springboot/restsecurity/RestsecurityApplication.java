package com.yym.springboot.restsecurity;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.yym.springboot.common.util.SpringUtil;
import com.yym.springboot.restsecurity.config.SecurityIgnoreUrlProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan(basePackages = "com.yym.springboot.restsecurity.mapper")
@EnableConfigurationProperties(SecurityIgnoreUrlProperties.class)
public class RestsecurityApplication {
    public static void main(String[] args)
    {
        SpringApplication springApplication = new SpringApplication(RestsecurityApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        // 将Context设置到SpringUtil中
        SpringUtil.setApplicationContext(configurableApplicationContext);
    }
}
