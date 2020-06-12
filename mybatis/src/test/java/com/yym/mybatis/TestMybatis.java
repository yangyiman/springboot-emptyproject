package com.yym.mybatis;

import com.yym.mybatis.dao.TbClassDao;
import com.yym.mybatis.dao.TbStudentDao;
import com.yym.mybatis.entity.TbClass;
import com.yym.mybatis.entity.TbStudent;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    private SqlSession sqlSession;
    private SqlSessionFactory factory;

    // 初始化sqlSession
    @Before
    public void initSqlSession() throws IOException {
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        // 创建sqlSessionFactoryBuilder类
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        // 读取配置文件,获取SqlSessionFactory类
        factory = factoryBuilder.build(is);
        // 获取sqlSession类
        sqlSession = factory.openSession();
    }

    // 结束sqlSession
    @After
    public void destroySqlSession(){
        if(sqlSession != null){
            sqlSession.close();
        }
    }

    // 拿到sqlSession,执行sql,并返回结果
    @Test
    public void runSqlSession(){
        TbClassDao tbClassDao = sqlSession.getMapper(TbClassDao.class);
        List<TbClassDao> list = tbClassDao.selectAllClass();
        System.out.println("list = " + list);
    }

    /**
     * 测试一级魂村,一旦有更新操作则缓存清除
     */
    @Test
    public void runSqlSessionOne(){
        TbClassDao mapper = sqlSession.getMapper(TbClassDao.class);
        TbClass tbClass = mapper.selectOne(1);
        System.out.println("tbClass = " + tbClass);
        TbClass tbClass1 = mapper.selectOne(1);
        System.out.println("tbClass1 = " + tbClass1);
    }

    /**
     * 测试二级缓存
     *
     * 二级缓存失效:
     * 1. 第一次sqlSession未提交,二级缓存不会生效
     * 2. 任何更新操作都会使缓存失效(insert,update,delete)
     * 3.
     */
    @Test
    public void runSqlSession2(){
        // 第一次从数据库中查询,然后存入缓存中
        TbClassDao mapper = sqlSession.getMapper(TbClassDao.class);
        TbClass tbClass = mapper.selectOne(2);
        System.out.println("tbClass = " + tbClass);
        sqlSession.commit();
        // 调用close()方法效果一样

        SqlSession sqlSession1 = factory.openSession();
        TbClassDao mapper1 = sqlSession1.getMapper(TbClassDao.class);
        TbClass tbClass1 = mapper1.selectOne(2);
        System.out.println("tbClass1 = " + tbClass1);
    }

    /**
     * 有更新情况下,会使二级缓存失效
     */
    /*

    @Test
    public void test(){
        // 先获取结果并放入缓存中
        TbClassDao mapper = sqlSession.getMapper(TbClassDao.class);
        TbClass tbClass = mapper.selectOne(2);
        System.out.println("tbClass = " + tbClass);
        sqlSession.commit();
        // 验证二级缓存
        SqlSession sqlSession1 = factory.openSession();
        TbClassDao mapper1 = sqlSession1.getMapper(TbClassDao.class);
        TbClass tbClass1 = mapper1.selectOne(2);
        System.out.println("tbClass1 = " + tbClass1);
        // 更新二级缓存
        TbClass tb = new TbClass().setClassName("2-cache").setStatus("enable").setId(2);
        SqlSession sqlSession2 = factory.openSession();
        TbClassDao mapper2 = sqlSession2.getMapper(TbClassDao.class);
        int count = mapper2.updateOne(tb);
        System.out.println("count = " + count);
        sqlSession2.commit();
        // 再次获取
        SqlSession sqlSession3 = factory.openSession();
        TbClassDao mapper3 = sqlSession3.getMapper(TbClassDao.class);
        TbClass tbClass3 = mapper3.selectOne(2);
        System.out.println("tbClass3 = " + tbClass3);
    }

     */


    /**
     * 测试mybatis嵌套结果
     * 连表查询,然后将结果一一映射
     */
    @Test
    public void nestResult(){
        TbStudentDao tbStudentDaoProxy = sqlSession.getMapper(TbStudentDao.class);
        TbStudent tbStudent = tbStudentDaoProxy.selectStudentByIdNestResult(3);
        System.out.println("tbStudent = " + tbStudent);
    }

    /**
     * 测试mybatis嵌套查询  resultMap关联 association collection
     * 执行多次查询,将结果拼凑在一起
     */
    @Test
    public void nestQuery(){
        TbStudentDao tbStudentDaoProxy = sqlSession.getMapper(TbStudentDao.class);
        TbStudent tbStudent = tbStudentDaoProxy.selectStudentByIdNestQuery(2);
        System.out.println("tbStudent = " + tbStudent);
    }
}
