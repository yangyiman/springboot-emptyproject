package com.yym.mybatis.dao;

import com.yym.mybatis.entity.TbClass;
import com.yym.mybatis.entity.TbStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbStudentDao {

    /**
     * 通过学生id查找学生
     * 嵌套查询,就是说多次查询,将结果拼接在一起
     * @param id
     * @return
     */
    TbStudent selectStudentByIdNestQuery(@Param("id") Integer id);

    TbClass selectClassById(@Param("id") Integer id);

    /**
     * 嵌套结果查询
     * @param id
     * @return
     */
    TbStudent selectStudentByIdNestResult(@Param("id")Integer id);
}
