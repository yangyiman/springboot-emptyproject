package com.yym.springboot.base.java.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程总共有4中方式:
 *  1. 继承Thread
 *  2. 实现Runnable
 *  3. 实现Callable --> 有返回值,并且可以抛出异常
 *  4. 线程池创建线程
 */
public class CreateThread {
}

class Thread1 extends Thread{

    @Override
    public void run() {
        System.out.println("Thread1.run");
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread2.run");
    }
}

class Thread3 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return null;
    }
}

class Thread4 {
    private ExecutorService pool = Executors.newCachedThreadPool();

    public void test(){
        pool.execute(()-> System.out.println("pool = " + pool));
    }
}