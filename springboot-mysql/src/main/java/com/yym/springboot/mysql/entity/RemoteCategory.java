package com.yym.springboot.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yym
 * @since 2020-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RemoteCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 一组分类的标识id,与根节点id相同
     */
    private String categoryUnionId;

    /**
     * 排序号,默认为null,从0开始排序
     */
    private Integer sort;

    /**
     * 根节点为1
     */
    private Integer level;

    /**
     * 分类位置,top1为栏目(默认),top2为一级分类,top3为二级分类
     */
    private String position;

    /**
     * 标签状态,默认为disable
     */
    private String status;

    /**
     * 有效开始时间
     */
    private LocalDateTime expireStartDate;

    /**
     * 有效结束时间
     */
    private LocalDateTime expireEndDate;

    /**
     * 置顶时间
     */
    private LocalDateTime topAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
