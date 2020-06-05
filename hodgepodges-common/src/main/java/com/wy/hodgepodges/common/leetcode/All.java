package com.wy.hodgepodges.common.leetcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-16 20:11
 */
public class All {

    /**
     * 相连最大和
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
        Lock lock;
        lock = new ReentrantLock();
        lock.lock();
        try {

        } finally {

            lock.unlock();
        }


        return null;
    }

    /**
     * 素数
     * @param num
     */
    public void primeNumber(int num){


    }

}
