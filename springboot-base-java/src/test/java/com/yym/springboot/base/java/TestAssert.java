package com.yym.springboot.base.java;

import org.junit.Assert;
import org.junit.Test;

public class TestAssert {
    @Test
    public void test1(){
        Object obj = null;
        Assert.assertNull("不能为空哦",obj);
    }
}
