package com.wy.hodgepodges.common.util;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-20 20:35
 */
public class NumberUtils {

    public static boolean isPrimeNumber(int num) {
        if (num <= 1) return false;
        if (num == 2)return true;
        for (int i=2;i<num;i++) {
            if (num/i==0) return false;
        }
        return true;
    }


}
