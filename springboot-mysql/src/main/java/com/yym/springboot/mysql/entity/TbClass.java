package com.yym.springboot.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TbClass {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String className;
    private String status;
    private LocalDateTime startedAt;

    @TableField(exist = false)
    private TbStudent tbStudent;
}
