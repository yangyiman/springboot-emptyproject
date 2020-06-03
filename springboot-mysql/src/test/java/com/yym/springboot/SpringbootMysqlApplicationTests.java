package com.yym.springboot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yym.springboot.mysql.SpringbootMysqlApplication;
import com.yym.springboot.mysql.entity.RemoteCategory;
import com.yym.springboot.mysql.entity.TbClass;
import com.yym.springboot.mysql.mapper.RemoteCategoryMapper;
import com.yym.springboot.mysql.mapper.TbClassMapper;
import com.yym.springboot.mysql.service.TbClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMysqlApplication.class)
public class SpringbootMysqlApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    private TbClassService tbClassService;
    @Autowired
    private TbClassMapper tbClassMapper;
    @Autowired
    private RemoteCategoryMapper remoteCategoryMapper;

    Map<String, List<Long>> map = new HashMap<>();
    List<Long> list1 = new ArrayList<>();
    List<Long> list2 = new ArrayList<>();
    List<Long> list3 = new ArrayList<>();
    List<Long> list5 = new ArrayList<>();

    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection.getClass());
    }

    // 如果存在id,就去数据库查找,是否存在数据,不存在就添加,存在就修改
    @Test
    public void test2() {
        TbClass tb = new TbClass();
        //tb.setId(1);
        tb.setClassName("究极一班");
        boolean b = tbClassService.saveOrUpdate(tb);
    }

    @Test
    public void test3() {
        TbClass tb = new TbClass();
        //tb.setId(1);
        //tb.setClassName("究极一班");
        LambdaQueryWrapper<TbClass> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TbClass::getClassName, "高一3班");
        TbClass obj1 = tbClassService.getObj(wrapper, obj -> tb);
        System.out.println("obj1 = " + obj1);
    }

    /**
     * 测试直接用updateById
     * 和直接使用sql修改谁快
     * <p>
     * 修改id=2的status为enable
     * <p>
     * SELECT id,class_name,status,started_at FROM tb_class WHERE id=?
     * UPDATE tb_class SET class_name=?, status=?, started_at=? WHERE id=?
     * 耗时: 417
     */
    @Test
    public void testUpdateTbClass() {

        remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");
        remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");

        RemoteCategory c = new RemoteCategory();
        c.setId("022987984f9d4882a90da8a22c9919ae");
        c.setStatus("enable");
        remoteCategoryMapper.updateById(c);

        System.out.println("============================================");
        System.out.println("============================================");
        System.out.println("============================================");

        long s1 = System.currentTimeMillis();
        RemoteCategory c1 = remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");
        if (c1.getStatus().equals("enable")) {
            c1.setStatus("disable");
        } else {
            c1.setStatus("enable");
        }
        remoteCategoryMapper.updateById(c1);
        long s11 = System.currentTimeMillis();
        list1.add(s11 - s1);
        map.put("updateById所有字段", list1);
        System.out.println("updateById所有字段所耗费时间 = " + (s11 - s1)); // 117  17 20  11 13 13 8 14 15 10

        System.out.println("**********************************");

        long s2 = System.currentTimeMillis();
        RemoteCategory c2 = remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");
        //TbClass tbClass1 = new TbClass();
        if (c2.getStatus().equals("enable")) {
            c2.setStatus("disable");
        } else {
            c2.setStatus("enable");
        }
        c2.setCategoryUnionId(null);
        c2.setCreatedAt(null);
        c2.setDescription(null);
        c2.setExpireEndDate(null);
        c2.setExpireStartDate(null);
        c2.setLevel(null);
        c2.setName(null);
        c2.setPosition(null);
        c2.setTopAt(null);
        c2.setSort(null);
        remoteCategoryMapper.updateById(c2);
        long s22 = System.currentTimeMillis();
        list2.add(s22 - s2);
        map.put("updateById置为null", list2);
        System.out.println("updateById置为null所耗费时间 = " + (s22 - s2)); // 16 16 21 11 15 13 16 13 16 13 11

        System.out.println("**********************************");

        long s5 = System.currentTimeMillis();
        RemoteCategory c5 = remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");
        RemoteCategory cc = new RemoteCategory();
        if (c5.getStatus().equals("enable")) {
            cc.setStatus("disable");
        } else {
            cc.setStatus("enable");
        }
        cc.setId(c5.getId());
        remoteCategoryMapper.updateById(cc);
        long s55 = System.currentTimeMillis();
        list5.add(s55 - s5);
        map.put("对象是new出来的", list5);
        System.out.println("对象是new出来的所耗费时间 = " + (s55 - s5)); // 14 26 14 14  21 15 11 11 10 11

        System.out.println("**********************************");

        long s3 = System.currentTimeMillis();
        RemoteCategory c3 = remoteCategoryMapper.selectById("022987984f9d4882a90da8a22c9919ae");
        if (c3.getStatus().equals("disable")) {
            remoteCategoryMapper.updateByIdInSql("022987984f9d4882a90da8a22c9919ae", "enable");
        } else {
            remoteCategoryMapper.updateByIdInSql("022987984f9d4882a90da8a22c9919ae", "disable");
        }
        long s33 = System.currentTimeMillis();
        list3.add(s33 - s3);
        map.put("使用sql", list3);
        System.out.println("使用sql所耗费时间 = " + (s33 - s3));

        System.out.println("**********************************");

        /**
         * 直接调用sql:
         * 12, 27, 12, 15, 7, 11, 6, 8, 10, 9, 9, 16, 16, 6, 6, 13, 9, 7, 8, 6, 9, 6, 7, 9, 8, 8, 8, 7, 9, 7, 6, 9, 9, 6, 6, 8, 6, 5, 6, 13, 12, 7, 8, 10, 7, 11, 7, 8, 10, 7
         * new出来的:
         * 11, 21, 23, 16, 13, 8, 10, 7, 12, 10, 7, 11, 25, 7, 8, 8, 9, 9, 6, 7, 6, 8, 7, 6, 6, 6, 6, 6, 8, 9, 8, 6, 11, 8, 9, 7, 6, 10, 6, 6, 11, 10, 6, 10, 9, 9, 6, 8, 7, 7
         * updateId所有字段:
         * 166, 15, 15, 42, 12, 11, 12, 9, 13, 7, 6, 7, 10, 9, 9, 8, 8, 8, 24, 8, 8, 8, 8, 9, 9, 8, 7, 11, 10, 8, 6, 6, 6, 9, 7, 10, 9, 7, 16, 6, 9, 9, 12, 11, 6, 5, 9, 8, 8, 11
         * updateid设置null
         * 20, 23, 22, 22, 10, 10, 16, 8, 10, 8, 7, 11, 6, 6, 6, 7, 6, 8, 5, 5, 6, 6, 7, 6, 6, 6, 6, 7, 6, 7, 7, 6, 7, 9, 7, 8, 7, 6, 11, 9, 10, 11, 10, 10, 6, 7, 21, 8, 6, 6
         */


        /*long s4 = System.currentTimeMillis();
        TbClass tbClass4 = tbClassMapper.selectById(2);
        if(tbClass4.getStatus().equals("enable")){
            tbClass4.setStatus("disable");
        }else {
            tbClass4.setStatus("enable");
        }
        tbClass4.setStartedAt(null);
        tbClass4.setClassName(null);
        LambdaUpdateWrapper<TbClass> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(TbClass::getId,tbClass4.getId());
        tbClass4.setId(null);
        tbClassMapper.update(tbClass4,wrapper);
        long s44 = System.currentTimeMillis();
        System.out.println("使用wrapper所耗费时间 = " + (s44-s4));  //139 238 180*/
    }

    /**
     * UPDATE tb_class SET status=? WHERE id=?
     */
    @Test
    public void testUpdateClass2() {
        TbClass tbClass = new TbClass();
        tbClass.setId(2);
        tbClass.setStatus("disable");
        long s = System.currentTimeMillis();
        tbClassMapper.updateById(tbClass);
        long s1 = System.currentTimeMillis();
        System.out.println("updateById只有status所耗费时间 = " + (s1 - s)); //254
    }

    /**
     * 直接调用sql
     */
    @Test
    public void testUpdateClass3() {
        long s = System.currentTimeMillis();
        tbClassMapper.updateByIdInSql(2, "enable");
        long s1 = System.currentTimeMillis();
        System.out.println("使用sql所耗费时间 = " + (s1 - s));  //186
    }

    /**
     * 结论: 直接使用sql是最高效的方式,但是new出来一个新对象是实现最简单的
     */
    @Test
    public void testUpdateClass4() {
        int size = 2000;
        for (int i = 0; i < size; i++) {
            this.testUpdateTbClass();
        }

        Set<Map.Entry<String, List<Long>>> entries = map.entrySet();
        for (Map.Entry<String, List<Long>> entry : entries) {
            String key = entry.getKey();
            System.out.println("次数 = " + size);
            System.out.println("key = " + key);
            List<Long> value = entry.getValue();
            System.out.println(key + "的总时长为: " + value.stream().reduce(0L, Long::sum));
            System.out.println(key + "的最短时长为: " + value.stream().min((i, j) -> Long.compare(i, j)).get());
            System.out.println(key + "的最长时长为: " + value.stream().max((i, j) -> Long.compare(i, j)).get());
            System.out.println(key + "的平均时长为: " + (double) (value.stream().reduce(0L, Long::sum) / 200L));
            System.out.println("value = " + value);
        }
    }
}
