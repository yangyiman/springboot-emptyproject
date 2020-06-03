package com.yym.springboot.base.java.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯: 用于并发线程数的控制,用于共享资源的互斥
 * acquire: 相当于加锁,要么获取信号量(信号量-1),要么一直等下去
 * release: 释放信号量(信号量+1),下面以抢车位为例,6个线程抢3个车位
 *
 * 0抢占了车位
 * 2抢占了车位
 * 1抢占了车位
 * 1释放了车位
 * 0释放了车位
 * 3抢占了车位
 * 4抢占了车位
 * 2释放了车位
 * 5抢占了车位
 * 3释放了车位
 * 4释放了车位
 * 5释放了车位
 */
public class MySemaphore {
    public static void main(String[] args) {
        // 模拟3个车位
        Semaphore semaphore = new Semaphore(3);

        // 开启6个线程
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    // 互斥
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢占了车位");
                    // 停2秒
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "释放了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
