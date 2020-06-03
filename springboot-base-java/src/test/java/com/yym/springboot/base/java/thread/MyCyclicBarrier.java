package com.yym.springboot.base.java.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 7龙珠
 * 最后一个同学离开,锁门
 * 0了离开教室
 * 1了离开教室
 * 2了离开教室
 * 3了离开教室
 * 4了离开教室
 * 5了离开教室
 * 5 锁了门
 */
public class MyCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(6,()->System.out.println(Thread.currentThread().getName()+" 锁了门"));
        for (int i = 0; i < 6; i++) {
            new Thread(()-> {System.out.println(Thread.currentThread().getName() + "了离开教室");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
