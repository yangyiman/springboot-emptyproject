package com.yym.springboot.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yym.springboot.mysql.entity.TbClass;

import java.util.List;

public interface TbClassService extends IService<TbClass> {
    void insertClass(TbClass tbClass);

    List<TbClass> getTbclass();

    TbClass selectOne(Integer id);

    void updateOne(TbClass tbClass);

    List<TbClass> selectOneByIdOrClassName(Integer id, String className);
}
