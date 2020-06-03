package com.yym.springboot.base.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有3个线程,分别为A,B,C,交替打印,A打印5次,B打印10,C打印15次
 */
public class ThreadLoopPrint {
    public static void main(String[] args) {
        PrintABC abc = new PrintABC();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                abc.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                abc.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                abc.printC();
                System.out.println(" ============= ");
            };
        },"C").start();
    }
}

class PrintABC{
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            // 不等于1,则等待
            if(flag != 1){
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 等于1则打印
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            // 打印完成,唤醒B
            flag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            // 不等于2,则等待
            if(flag != 2){
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 等于2则打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            // 打印完成,唤醒C
            flag = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            // 不等于3,则等待
            if(flag != 3){
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 等于3则打印
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            // 打印完成,唤醒A
            flag = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }
}
