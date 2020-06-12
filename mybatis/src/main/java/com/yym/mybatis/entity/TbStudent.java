package com.yym.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TbStudent {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private TbClass tbClass;
}
