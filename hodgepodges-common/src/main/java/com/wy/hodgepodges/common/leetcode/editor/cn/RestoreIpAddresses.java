//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
//
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。 
//
// 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法 
// 👍 290 👎 0


package com.wy.hodgepodges.common.leetcode.editor.cn;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses {
    LinkedList<String> linkedList = Lists.newLinkedList();
    ArrayList<String> result = Lists.newArrayList();

    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();
        solution.restoreIpAddresses("25525511135");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> list = Lists.newArrayList();
            char[] chars = s.toCharArray();
            if (s.length()>12 || s.length()<4) return null;
            
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}