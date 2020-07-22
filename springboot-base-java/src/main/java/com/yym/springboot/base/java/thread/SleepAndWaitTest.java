package com.yym.springboot.base.java.thread;

/**
 * 证明sleep没有释放锁,wait释放锁
 *
 * wati一定要加synchronize,不然报错 Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
 */
public class SleepAndWaitTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        SleepAndWaitTest test = new SleepAndWaitTest();
        /**
         * 因为是通一把锁lock,如果wait没有释放锁,那就不会执行notify的方法,也就不会有后面的输出,
         * 所有可以证明wait方法是释放锁的
         */
        //test.testWaitLock(); // wait 开始 唤醒开始 唤醒开始 wait结束
        /**
         * 同一把锁,显然sleep没有释放锁,所以硬是等了2s多
         */
        test.testSleepLock(); // sleep开始 sleep结束 唤醒开始 wait结束
    }

    private void testSleepLock() throws InterruptedException {
        new Thread(() -> {
            synchronized (lock){
                try {
                    System.out.println("sleep 开始");
                    Thread.sleep(2000);
                    System.out.println("sleep 结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(200);
        this.doNotify();
    }

    public void testWaitLock() throws InterruptedException {
        new Thread(() -> {
            try {
                this.doWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
        this.doNotify();
    }

    private void doWait() throws InterruptedException {
        synchronized (lock){
            System.out.println("wait 开始");
            lock.wait();
            System.out.println("wait 结束");
        }
    }

    private void doNotify() {
        synchronized (lock){
            System.out.println("唤醒 开始");
            lock.notify();
            System.out.println("唤醒 结束");

        }
    }
}
