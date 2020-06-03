package com.yym.mybatis.dao;

import com.yym.mybatis.entity.TbClass;

import java.util.List;

public interface TbClassDao {

    List<TbClassDao> selectAllClass();

    TbClass selectOne(Integer id);

    int updateOne(TbClass tbClass);
}
