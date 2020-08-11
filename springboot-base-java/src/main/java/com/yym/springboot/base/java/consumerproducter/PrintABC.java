package com.yym.springboot.base.java.consumerproducter;

import javax.swing.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程轮流打印ABC
 */
public class PrintABC {
    public static void main(String[] args) {
        Print p = new Print();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    p.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"一").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    p.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"二").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    p.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"三").start();
    }
}

class Print {
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    int order = 1; // 1A 2B 3C

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (order != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印AAA");
            order = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (order != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印BBB");
            order = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (order != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印CCC");
            order = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }
}
