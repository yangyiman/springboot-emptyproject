package com.yym.springboot.base.java.juc;

/**
 * volatile   保准内存可见性
 * 内存可见性问题: 当多个线程操作共享数据时,彼此不可见
 *
 * 相比于synchronized:
 * 1. 不具有排斥性
 * 2. 不保证数据的原子性
 *
 * 原子性问题解决:
 * 使用原子变量
 */
public class TestVolatile {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        new Thread(mt).start();

        while (true){
            /*synchronized (mt){
            }*/
            if(mt.getFlag()){
                System.out.println("====================");
                break;
            }
        }

    }

}

class MyThread implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
        flag = true;
        if(flag){
            System.out.println("flag = " + flag);
        }
    }

    public boolean getFlag(){
        return flag;
    }
}
