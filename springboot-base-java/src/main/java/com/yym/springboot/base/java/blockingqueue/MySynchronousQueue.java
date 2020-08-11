package com.yym.springboot.base.java.blockingqueue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列,与其他阻塞队列不同,它不存储元素,put进去一个元素,必须take取出来之后才能再次put
 */
public class MySynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            while (true){
                try {
                    System.out.println(Thread.currentThread().getName()+"添加元素");
                    queue.put(UUID.randomUUID().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                try {
                    System.out.println(Thread.currentThread().getName()+"取出元素");
                    TimeUnit.SECONDS.sleep(2);
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
