package com.yym.springboot.autoconfig.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Cat {
    private String name = "小黑";
}
