package com.yym.springboot.web.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String gender;
}
