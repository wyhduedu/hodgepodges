package com.wy.hodgepodges;

/**
 * @author wy
 * @version V1.0
 * @desc 滑动窗口算法
 * @date 2019-11-26 17:43
 */
public class All {

    public static void main(String[] args) {
//        int[] arr = {100, 200, 300, 400,600};
//        System.out.println(aaa(arr,2));
        System.out.println("sdsdj".matches("^[A-Za-z0-9]{15,20}$"));

    }

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

    /**
     * 最短子字符串
     * @param s
     * @param t
     * @return
     */
    static String getStr(String s,String t){
        char[] chars = s.toCharArray();
        char[] te = t.toCharArray();




        return null;
    }
}
