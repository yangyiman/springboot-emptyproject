package com.yym.springboot.base.java;

import org.junit.Test;

import java.util.Random;

/**
 * 测试随机数
 * 1. 时间毫秒数
 * 2. Math.random
 * 3. Random
 */
public class RandomTest {

    // 使用时间毫秒数实现[0,100)的随机数
    @Test
    public void testMillis(){
        long l = System.currentTimeMillis();
        System.out.println("随机数 = " + l%100);
    }

    // 生成[0,1)的随机数
    @Test
    public void testMath(){
        double random = Math.random();
        System.out.println("random = " + random);
    }

    // 生成指定的随机数
    @Test
    public void testRandom(){
        Random random = new Random();
        int i = random.nextInt(20);
        System.out.println("i = " + i);
    }
}
