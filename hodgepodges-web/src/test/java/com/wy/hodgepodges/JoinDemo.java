package com.wy.hodgepodges;

import com.google.common.collect.Maps;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/6/5 11:27 上午
 */
public class JoinDemo extends Thread{
    int i;
    Thread previousThread; //上一个线程
    public JoinDemo(Thread previousThread,int i){
        this.previousThread=previousThread;
        this.i=i;
    }
    @Override
    public void run() {
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name->"+Thread.currentThread().getName());
        System.out.println("num:"+i);
    }

    public static void main(String[] args) throws Exception {
        Thread previousThread=Thread.currentThread();
        for(int i=0;i<10;i++){
            JoinDemo joinDemo=new JoinDemo(previousThread,i);
            joinDemo.setName("joinDemo"+i);
            joinDemo.start();
            previousThread=joinDemo;
        }

        CyclicBarrier cyclicBarrier =  new CyclicBarrier(5);
        CountDownLatch countDownLatch =new CountDownLatch(5);
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println("djdj");
        cyclicBarrier.await();
        System.out.println(  cyclicBarrier.getNumberWaiting());
        ConcurrentMap map = Maps.newConcurrentMap();
        Callable callable = () -> {
            System.out.println("djdj");
            System.out.println("djdj");
            return null;
        };
        callable.call();
        CountDownLatch ab = new  CountDownLatch(2);
        ab.countDown();

    }
}