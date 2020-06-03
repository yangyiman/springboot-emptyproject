package com.yym.springboot.base.java.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
}
