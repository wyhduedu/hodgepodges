package com.wy.hodgepodges;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @version V1.0
 * @desc 滑动窗口算法
 * @date 2019-11-27:43
 */
public class All {


    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = All.class.getClassLoader();
        System.out.println(loader);
        //一、 使用ClassLoader.loadClass()来加载类，不会执行初始化块
//        loader.loadClass("All");
        //二、 使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("All");
        //三、使用Class.forName()来加载类，指定ClassLoader，初始化时不执行静态块
        Class.forName("All", false, loader);
    }

    static {
        System.out.println("我是静态代码块。。。。");
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
