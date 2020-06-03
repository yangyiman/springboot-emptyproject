package com.yym.springboot.base.java.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现callable实现多线程
 */
public class CallableAndFutureTask {
    public static void main(String[] args){
        Mycallable mycallable = new Mycallable();
        // 使用FutureTask接受返回值
        FutureTask<Integer> task = new FutureTask<>(mycallable);

        // 开启多线程
        new Thread(task).start();

        // 获取结果
        Integer sum = null;
        try {
            sum = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("sum = " + sum);
    }
}

/**
 * 计算1到50的值
 */
class Mycallable implements Callable<Integer>{
    int sum = 0;
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 51; i++) {
            System.out.println("i = " + i);
            sum += i;
        }
        return sum;
    }
}
