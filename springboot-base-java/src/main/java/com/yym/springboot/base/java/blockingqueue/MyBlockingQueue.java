package com.yym.springboot.base.java.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列,FIFO,始终都是操作头数据
 * 四组api
 *  1. 队列满了,抛出异常
 *  2. 队列满了,有返回值,不抛出异常
 *  3. 一直等待
 *  4. 超时等待
 */
public class MyBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
//        MyTest.fullException();
//        MyTest.fullNoException();
//        MyTest.fullWaitForever();
        MyTest.fullWaitTimeout();
    }
}

class MyTest {
    private static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

    /**
     * 队列满了,再次添加报异常
     */
    public static void fullException() {
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        // IllegalStateException: Queue full
        //arrayBlockingQueue.add("d");
        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.remove()); //a
        System.out.println(arrayBlockingQueue.remove()); //b
        System.out.println(arrayBlockingQueue.remove()); //c
    }

    /**
     * 队列满了,再次添加失败,不报错
     */
    public static void fullNoException() {
        System.out.println(arrayBlockingQueue.offer("1"));
        System.out.println(arrayBlockingQueue.offer("2"));
        System.out.println(arrayBlockingQueue.offer("3"));
        // false,没有插入,并没有报错
        System.out.println(arrayBlockingQueue.offer("4"));

        // 取头一个数字
        System.out.println(arrayBlockingQueue.poll()); // 1
        System.out.println(arrayBlockingQueue.poll()); // 2
        System.out.println(arrayBlockingQueue.poll()); // 3
        // null
        System.out.println(arrayBlockingQueue.poll());
    }

    /**
     * 队列满了,一直等待
     * @throws InterruptedException
     */
    public static void fullWaitForever() throws InterruptedException {
        arrayBlockingQueue.put("z");
        arrayBlockingQueue.put("zz");
        arrayBlockingQueue.put("zzz");
        // 队列没有位置,一直阻塞
        arrayBlockingQueue.put("zzzz");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
    }

    public static void fullWaitTimeout() throws InterruptedException {
        System.out.println(arrayBlockingQueue.offer("1"));
        System.out.println(arrayBlockingQueue.offer("2"));
        System.out.println(arrayBlockingQueue.offer("3"));
        // 等待2s就退出,不会一直阻塞
        System.out.println(arrayBlockingQueue.offer("4", 2, TimeUnit.SECONDS));

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        // 超过3秒没元素就退出
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
    }


}
