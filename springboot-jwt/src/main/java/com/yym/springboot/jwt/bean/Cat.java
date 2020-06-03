package com.yym.springboot.jwt.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Cat {
    private String name;
    private Integer age;
}
