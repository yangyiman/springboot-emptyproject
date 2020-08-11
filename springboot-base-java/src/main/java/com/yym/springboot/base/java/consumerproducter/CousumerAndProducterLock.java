package com.yym.springboot.base.java.consumerproducter;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者问题
 *  三部曲: 等待 业务 通知
 *
 *案例:
 *  对一个数,以0为界限,进行加减
 */
public class CousumerAndProducterLock {
    public static void main(String[] args) {
        Num2 n = new Num2();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    n.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    n.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

@Data
class Num2 {
    private int num = 0;

    final Lock lock = new ReentrantLock();
    Condition inCondition = lock.newCondition();
    Condition deCondition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 此处必须用while,不然会有虚假唤醒问题
            while (num != 0) {
                inCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + "对num进行了加操作,当前值为" + ++num);
            // 通知减
            deCondition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 此处必须用while,不然会有虚假唤醒问题
            while (num == 0) {
                deCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + "对num进行了减操作,当前值为" + --num);
            // 通知加
            inCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
