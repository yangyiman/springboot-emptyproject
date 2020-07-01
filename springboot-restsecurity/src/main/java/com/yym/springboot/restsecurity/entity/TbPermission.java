package com.yym.springboot.restsecurity.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yym
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String tbPermission;

    private String tbMethod;

    private String tbUrl;

}
