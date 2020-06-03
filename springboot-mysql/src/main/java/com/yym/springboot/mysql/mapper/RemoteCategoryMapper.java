package com.yym.springboot.mysql.mapper;

import com.yym.springboot.mysql.entity.RemoteCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yym
 * @since 2020-01-19
 */
public interface RemoteCategoryMapper extends BaseMapper<RemoteCategory> {

    void updateByIdInSql(@Param("id") String id, @Param("status") String status);
}
