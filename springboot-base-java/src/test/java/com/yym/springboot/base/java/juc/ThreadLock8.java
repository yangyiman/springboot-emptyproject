package com.yym.springboot.base.java.juc;

import java.util.concurrent.TimeUnit;

/**
 * 线程8锁:
 *  有一个手机,判断是先打印email还是短信
 *
 *  1. 2个加锁方法,同1个对象?  邮件 短信
 *  2. email方法睡4秒,同1个对象?  邮件 短信
 *  3. 1个加锁方法睡4秒钟,1个普通方法,同1个对象?  游戏  邮件
 *  4. 2个加锁方法,2个对象?  短信 邮件
 *  5. 2个加锁静态方法,1个对象? 邮件 短信
 *  6. 2个加锁静态方法,2个对象? 邮件 短信
 *  7. 1个加锁静态方法,1个加锁方法,1个对象? 短信 邮件
 *  8. 1个加锁静态方法,1个加锁方法,2个对象? 短信 邮件
 *
 *
 * 结论:
 *  加锁是指与同一类方法互斥,比如普通同步方法之间互斥,但是不影响普通方方法(参见例子3)和静态同步方法(参见7);
 *  静态同步方法锁的是Class类,而普通同步方法锁的是当前对象,并且只对同类方法互斥
 */
public class ThreadLock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(()->{
//            phone.sendEmail();
            phone.sendEmailStatic();
        },"A").start();

        Thread.sleep(100);

        new Thread(()->{
            phone1.sendMessage();
//            phone.playGame();
//            phone1.sendMessage();
//            phone1.sendMessageStatic();
        },"B").start();
    }
}

class Phone {
    public synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发邮件");
    }

    public synchronized void sendMessage(){
        System.out.println("发短信");
    }

    public static synchronized void sendEmailStatic(){
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发邮件");
    }

    public static synchronized void sendMessageStatic(){
        System.out.println("发短信");
    }

    public void playGame(){
        System.out.println("打游戏");
    }
}
