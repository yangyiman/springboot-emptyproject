package com.yym.springboot.base.java.juc;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 倒计数器,到0之后执行等待方法
 *
 * countDown 每次-1
 * await 0之后执行的方法
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(5);
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName()+"执行了"+i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName()+"执行了"+i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        latch.await();
        System.out.println("售空...");
    }
}
