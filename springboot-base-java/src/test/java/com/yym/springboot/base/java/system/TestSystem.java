package com.yym.springboot.base.java.system;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 测试系统的环境变量
 */
public class TestSystem {
    @Test
    public void test(){
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey()+  "= = " + entry.getValue());
        }

    }
}
