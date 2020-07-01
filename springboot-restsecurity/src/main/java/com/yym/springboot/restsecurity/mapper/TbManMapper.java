package com.yym.springboot.restsecurity.mapper;

import com.yym.springboot.restsecurity.entity.TbMan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yym.springboot.restsecurity.entity.TbPermission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yym
 * @since 2020-06-30
 */
public interface TbManMapper extends BaseMapper<TbMan> {

    TbMan selectOneByUsername(String username);

    List<TbPermission> selectPermissionByUsername(String username);
}
