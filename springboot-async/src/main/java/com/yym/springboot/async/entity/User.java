package com.yym.springboot.async.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class User {
    @NotBlank(message = "名字不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;
}
