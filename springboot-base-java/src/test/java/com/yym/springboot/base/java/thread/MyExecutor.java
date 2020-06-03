package com.yym.springboot.base.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java的线程池是又Executor框架实现的,该框架中实现了Executor,Executors,ExecutorService,ThreadPoolExecutor这几个类
 * 线程池最重要的实现就是ThreadPoolExector这个;
 * 实际都不用这些去创建
 *
 * 1. 固定线程池
 * 2. 单例线程池
 * 3. 可变线程池
 * 4. 自定义线程池
 */
public class MyExecutor {
    public static void main(String[] args) {
        // 固定线程数池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 单例线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 可扩展的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                final int e = i;
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " -- threadPool == " + e));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
