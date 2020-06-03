package com.yym.springboot.base.java.threadLocal;

/**
 * ThreadLocal是一种方便各个线程管理自己状态的机制,他不是解决线程共享问题,也不是协调线程同步
 * 每个ThreadLocal内部有一个ThreadLocalMap,它的key就是当前ThreadLocal的对象该类才是实现线程隔离机制的关键。get()、set()、remove()都是基于该内部类进行操作，ThreadLocalMap用键值对方式存储每个线程变量的副本，key为当前的ThreadLocal对象，value为对应线程的变量副本。
 * 试想，每个线程都有自己的ThreadLocal对象，也就是都有自己的ThreadLocalMap，对自己的ThreadLocalMap操作，当然是互不影响的了，这就不存在线程安全问题了，所以ThreadLocal是以空间来交换安全性的解决思路
 */

/**
 * ThreadLocal为什么会内存泄漏
 *
 */
public class MyThreadLocal {

    public static void main(String[] args) {
        ThreadLocalCount t = new ThreadLocalCount();

    }
}

class ThreadLocalCount<Integer> extends ThreadLocal<java.lang.Integer> {
    /**
     * 返回初始化结果
     * @return
     */
    @Override
    protected java.lang.Integer initialValue() {
        return 0;
    }

    public int createdCount(){
        set(get()+1);
        return get();
    }
}

class MyThread extends Thread{
    private ThreadLocalCount count;
    public MyThread(ThreadLocalCount count){
        this.count = count;
    }

    /**
     * 每调用一次更新一下
     */
    @Override
    public void run() {
        if(null != count){
           count.createdCount();
           count.createdCount();
           count.createdCount();
        }
    }
}