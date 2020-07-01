package com.yym.springboot.vue.api.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class JdBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客id",hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private Integer jdUserId;

    @ApiModelProperty("博客名称")
    @NotBlank(message = "博客名称不能为空")
    private String title;

    /**
     * 摘要
     */
    @ApiModelProperty("博客摘要")
    @NotBlank(message = "博客摘要不能为空")
    private String decription;

    /**
     * 正文
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime updatedAt;


}
