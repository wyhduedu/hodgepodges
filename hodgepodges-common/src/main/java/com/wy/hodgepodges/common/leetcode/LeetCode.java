package com.wy.hodgepodges.common.leetcode;

/**
 * @author wy
 * @version V1.0
 * @desc leetcode解题
 * @date 2020/5/17 10:26 下午
 */
public class LeetCode {

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(matrix.length);
//        longestIncreasingPath(matrix);
    }

    /**
     * 求数组的
     * 最长上升子序列
     *
     * @return
     */
    public Integer lengthOfLIS() {

        return null;
    }


    /**
     * 给定一个整数矩阵，找出最长递增路径的长度。
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     * 示例 1:
     * 输入: nums =
     * [
     * [9,9,4],
     * [6,6,8],
     * [2,1,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径为 [1, 2, 6, 9]。
     *
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0) return 0;

        //1、找到最大的  ->找到最小的->
        int [] result = null;
        result[0]= 9;
        for (int[] num : matrix) {

        }


        return result.length;
    }


}
