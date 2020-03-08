package com.wy.hodgepodges.demo;

import sun.jvm.hotspot.runtime.Threads;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-03 09:55
 */
public class Test1 {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(() -> System.out.println(index));
        }
    }
}
