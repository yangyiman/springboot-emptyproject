package com.yym.springboot.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yym.springboot.mysql.entity.TbClass;
import org.apache.ibatis.annotations.Param;

public interface TbClassMapper extends BaseMapper<TbClass> {
    void updateByIdInSql(@Param("id") int id,@Param("status") String status);
}
