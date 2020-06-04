package com.yym.springboot;

import com.yym.springboot.mysql.SpringbootMysqlApplication;
import com.yym.springboot.mysql.entity.TbUser1;
import com.yym.springboot.mysql.entity.TbUser2;
import com.yym.springboot.mysql.mapper.TbUser1Mapper;
import com.yym.springboot.mysql.service.ITbUser1Service;
import com.yym.springboot.mysql.service.ITbUser2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMysqlApplication.class)
public class SpringbootMysqlApplicationTests {

    @Autowired
    private TbUser1Mapper tbUser1Mapper;
    @Autowired
    private ITbUser1Service iTbUser1Service;
    @Autowired
    private ITbUser2Service iTbUser2Service;

    // 测试MP的自增主键
    @Test
    public void insertUser1Test(){
        TbUser1 user1 = new TbUser1().setName("张三");
        tbUser1Mapper.insert(user1);
        System.out.println("user1 = " + user1);
    }

    /**
     * 测试方法支持事务,下面方法可以回滚
     */
    @Test
    @Transactional
    public void requiredTest(){
        TbUser2 user2 = new TbUser2().setName("zz");
        iTbUser2Service.insertRequired2Exception(user2);
    }

    /**
     * 传播方式为required,分别插入,try-catch
     *
     * 外部事务加事务,内部报错被try-catch时依然会回滚
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredInnerTest(){
        TbUser1 user1 = new TbUser1().setName("user1-test1");
        iTbUser1Service.insertRequired(user1);

        TbUser2 user2 = new TbUser2().setName("user2-test1");
        try {
            iTbUser2Service.insertRequired2Exception(user2);
        } catch (Exception e) {
            System.out.println("是否会回滚");
        }
    }
}
