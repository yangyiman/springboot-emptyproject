package com.yym.springboot.base.java.consumerproducter;

import lombok.Data;

/**
 * 生产者和消费者问题
 *
 *案例:
 *  对一个数,以0为界限,进行加减
 */
public class CousumerAndProducterSynchronized {
    public static void main(String[] args) {
        Num n = new Num();
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
class Num {
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        // 此处必须用while,不然会有虚假唤醒问题
        while (num != 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "对num进行了加操作,当前值为" + ++num);
        // 通知其他人来减
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 此处必须用while,不然会有虚假唤醒问题
        while (num == 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "对num进行了减操作,当前值为" + --num);
        // 通知其他人来减
        this.notifyAll();
    }


}
