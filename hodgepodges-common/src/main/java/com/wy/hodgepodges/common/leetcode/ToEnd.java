package com.wy.hodgepodges.common.leetcode;

/**
 * @author wy
 * @version V1.0
 * @desc 到达终点
 * @date 2019-11-29 10:35
 */
public class ToEnd {

    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        int a = sx + sy;
        if (sx > tx || sy > ty) {
            return false;
        }

        if ((a == tx && sy == ty) || (a == ty && sx == tx)) {
            return true;
        } else {
            if (reachingPoints(a, sy, tx, ty)) {
                return true;
            } else {
                reachingPoints(sx, a, tx, ty);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(reachingPoints(35,
                13,
                5547,
                84));
    }
}
