package com.yym.springboot.jwt.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean isBoss;
    private Map<String,Object> map;
    private List<Object> list;
    private Dog dog;
}
