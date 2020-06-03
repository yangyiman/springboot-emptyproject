package com.yym.springboot.redis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class Cat implements Serializable {
    private String name;
    private Integer age;

}
