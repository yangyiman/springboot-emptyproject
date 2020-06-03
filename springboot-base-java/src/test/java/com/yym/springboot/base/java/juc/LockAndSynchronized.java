package com.yym.springboot.base.java.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决多线程线程安全一般会加锁,加锁方式一般为2终:
 * 1. 使用sychronized关键字  ----隐式加锁
 * 1.1 同步代码块
 * 1.2 同步方法
 * 2. 使用lock锁 ----显式加锁
 * 通过lock上锁,通过unlock解锁
 */
public class LockAndSynchronized {
    public static void main(String[] args) {
        Ticket t = new Ticket();

        new Thread(t, "1号窗口").start();
        new Thread(t, "2号窗口").start();
        new Thread(t, "3号窗口").start();
    }
}

class Ticket implements Runnable {
    private int t = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();

        try {
            while (t > 0) {
                System.out.println("t = " + t--);
            }
        } finally {
            lock.unlock();
        }
    }
}


class Ticket2 implements Runnable {
    private int t = 100;

    @Override
    public void run() {
        while (t > 0) {
            System.out.println("t = " + t--);
        }
    }
}