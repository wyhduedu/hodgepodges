/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * <p>
 * <p>
 * 注意：
 * <p>
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 * <p>
 * Related Topics 数组
 */

package com.wy.hodgepodges.common.leetcode.editor.cn;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        Solution solution = new MaxConsecutiveOnes().new Solution();
        int[] nums={1,1,0,1,1,1};
        solution.findMaxConsecutiveOnes(nums);
    }

    //      leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {


            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}