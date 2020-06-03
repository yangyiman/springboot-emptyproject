package com.yym.springboot.base.java.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 班长锁门
 * 控制线程顺序: CountDownLatch 闭锁: 主要有两个方法,countDown和await,线程执行一次,countDown就会执行一次,去减1,直到减为0,被await的线程才会激活
 * 以班长锁门为例: 需要所有同学都走完,才可以锁门,此时需要用到闭锁控制线程执行顺序
 *
 * 0了离开教室
 * 1了离开教室
 * 2了离开教室
 * 3了离开教室
 * 4了离开教室
 * 5了离开教室
 * main 锁了门
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()-> {System.out.println(Thread.currentThread().getName() + "了离开教室");latch.countDown();},String.valueOf(i)).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+" 锁了门");
    }
}

