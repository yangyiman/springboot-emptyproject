package com.yym.springboot.base.java.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 正计数器,达到预设值之后的方法
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 表示达到5个之后,执行方法
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("OK");
        });
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
