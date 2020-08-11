package com.yym.springboot.base.java.ThreadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池 3大方法,7大参数,4种策略
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ThreeMethod.test3();
    }
}

//3大方法
class ThreeMethod {
    //3大方法
    public static void test1() {
        ExecutorService cachePool = Executors.newCachedThreadPool();
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
    }

    //7大参数
    public static void test2() {
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(
                5,
                20,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20),
                new ThreadPoolExecutor.AbortPolicy());
    }
    // 4种策略
//    ThreadPoolExecutor.AbortPolicy()  超过处理能力最大值,直接抛异常
//    ThreadPoolExecutor.CallerRunsPolicy() 那个线程过来的,交给那个线程执行
//    ThreadPoolExecutor.DiscardPolicy()    直接丢弃
//    ThreadPoolExecutor.DiscardOldestPolicy()  与最早的竞争,也不会抛出异常

    // 池的大小
    // IO密集型和CPU密集型
    // CPU根据核数来,多少核的CPU,池就多大
    // IO程序中IO操作比较大的任务数
    public static void test3(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
