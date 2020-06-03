package com.yym.springboot.base.java.thread;

import java.util.concurrent.Callable;

/**
 * synchronized 锁的是当前this,同一时刻,只有一个线程可以访问同一个类下多个synchronized修饰的唯一一个方法;对于普通方法,多个线程可以分别调用多个普通方法;
 * 当时相对于静态synchronized方法,锁的是类模板,一个类只有一个模板,类似于锁的是.class
 * 静态同步方法锁定的是整个类,而静态方法锁定的是该类的示例,如果静态同步方法,和同步方法同时调用,但是他们锁的对象不一致,也不构成竞争,所以是按调用先后执行的
 */
public class MyThread {
    public static synchronized void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();
    }
}

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return null;
    }
}
