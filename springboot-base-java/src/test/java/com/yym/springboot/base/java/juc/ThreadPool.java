package com.yym.springboot.base.java.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * Executor
 *  |---ExecutorService 子接口: 线程池的主要接口
 *       |--- ThreadPoolExecutor 线程池的实现类
 *       |--- ScheduledExecutorService 子接口,线程调度
 *             |-- ScheduledThreadPoolExecutor 实现类,可用于线程调度,也可以当线程池使用
 *
 * Executors线程工具类创建线程池
 *
 *  ExecutorService newFixedThreadPool() : 创建固定大小线程池
 *  ExecutorService newCachedThreadPool() : 创建缓存线程池,不固定线程数量,可以根据需求更改线程数量
 *  ExecutorService newSingleThreadPool() : 单例线程池
 *  ScheduledExecutorService newScheduledThreadPool() : 固定大小线程池,可以延迟或者定时置顶任务
 */
public class ThreadPool {
    public static void main(String[] args) {
        // 创建线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // 立即提交线程
        //scheduledExecutorService.submit(new MyThreadPool());
        System.out.println("scheduledExecutorService ====== ");
        // 定时执行
        scheduledExecutorService.schedule(new MyThreadPool(), 5, TimeUnit.SECONDS);
        // 关闭池子
        scheduledExecutorService.shutdown();
    }
}

class MyThreadPool implements Runnable{
    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);
    }
}



