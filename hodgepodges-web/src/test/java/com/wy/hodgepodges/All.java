package com.wy.hodgepodges;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @version V1.0
 * @desc 滑动窗口算法
 * @date 2019-11-26 17:43
 */
public class All {

    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 400, 600};
//        System.out.println(aaa(arr,2));
        System.out.println("sdsdj".matches("^[A-Za-z0-9]{15,20}$"));
        Lock lock = new Lock() {
            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @Override
            public Condition newCondition() {
                return null;
            }
        };

    }

    /**
     * 相连最大和
     *
     * @param arr
     * @param k
     * @return
     */
    static int aaa(int[] arr, int k) {
        int len = arr.length;
        if (len < k) {

            return -1;
        }
        synchronized (arr) {
            int maxSum = 0;
            for (int i = 0; i < k; i++) {
                maxSum += arr[i];
            }
            int sum = maxSum;
            for (int i = k; i < len; i++) {
                sum += arr[i] - arr[i - k];
                maxSum = Math.max(maxSum, sum);
            }
            return maxSum;
        }
    }

    /**
     * 最短子字符串
     *
     * @param s
     * @param t
     * @return
     */
    static String getStr(String s, String t) {
        char[] chars = s.toCharArray();
        char[] te = t.toCharArray();
        Lock lock = new ReentrantLock();

        lock.lock();
        return null;
    }


}
