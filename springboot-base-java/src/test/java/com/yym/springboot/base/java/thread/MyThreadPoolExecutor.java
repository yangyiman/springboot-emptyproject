package com.yym.springboot.base.java.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池的最大线程数看maximumPoolSize,最大容量数是队列加最大线程,大于其就开启拒绝策略
 *
 * 拒绝策略:
 *  1. AbortPolicy:超过就报RejectExecutionException异常,阻止系统运行
 *  2. CallerRunsPolicy:既不抛出异常,也不放弃任务,而是将某些任务回到到调用者,从而降低新任务的流量,比如:主线程调用线程池,线程池处理不了任务,就回将任务给主线程执行
 *  3. DiscardOldestPolicy:抛弃队列中等待时间最长的任务,然后把当前任务加入队列中,尝试再次提交当前任务
 *  4. DiscardPolicy:默默丢弃无法处理的任务,不予任何处理也不抛出异常,如果允许任务丢失,这种策略是最好的
 *
 *  Runtime.getRuntime().availableProcessors()可以获取处理器数量,即核心线程数
 *  最大线程数CPU数＋1
 */
public class MyThreadPoolExecutor {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors()+1,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }

    @Test
    public void test1(){
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);
    }
}
