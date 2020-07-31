/**给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 示例:
 给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
Related Topics 数组 哈希表
*/


package com.wy.hodgepodges.common.leetcode.editor.cn;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        int[]nums={2, 7, 11, 15};
        solution.twoSum(nums,9);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[]result={0,0};
            boolean flag=false;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i]>target)continue;
                for (int j = i+1; j <nums.length ; j++) {
                    if (target-nums[i]==nums[j]) {
                        result[0] = i;
                        result[1] = j;
                        flag=true;
                    }
                }
            }
            if (flag)return result;
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}