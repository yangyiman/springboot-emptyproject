package com.yym.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Mybatis二级缓存存储的对象必须要序列化
 */
@Data
@Accessors(chain = true)
public class TbClass implements Serializable {
    private Integer id;
    private String className;
}
