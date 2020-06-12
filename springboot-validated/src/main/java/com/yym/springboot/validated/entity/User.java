package com.yym.springboot.validated.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class User {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Min(value = 0,message = "年龄不能小于0岁")
    private Integer age;
    private String gender;
    private String address;
}
