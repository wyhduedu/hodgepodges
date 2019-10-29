package com.wy.hodgepodge.common.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {
    public List<String> findRepeatedDnaSequences(String s) {
        //ACGT的SCII码的二进制表示，由此可知取后2位或者3位我们可以区分这4个字母
        //这里我们以取后3位来区分
        //     A: 1000001
        //     C: 1000011
        //     G: 1000111
        //     T: 1010100
        //记录不是第一次遍历到的结果
        Set<String> result = new HashSet();
        //记录第一次遍历到的结果
        Set<Integer> visited = new HashSet();
        for (int i = 0; i < s.length() - 9; i++) {
            int sum = 0;
            for (int j = i; j < i + 10; j++) {
                //int占4个字节，每个字节8位，共32位，左移3位，低位空出3位补0用来保存字符
                sum = sum << 3;
                //0x7为十六进制的7，是0111，刚好3位1，我们可以与字符做按位与操作以使用字符的高位都变成0
                //将字符转成低3位就能识别的数
                //例如A:1000001 & 0111 变成0000001
                int tmp = s.charAt(j) & 0x7;
                //按位或操作放入sum
                sum = sum | tmp;
                //0x3FFFFFFF为0011 1111 1111 1111 1111 1111 1111 1111刚好30位1，做按位与操作,此步做不做都一样
                //sum = sum & 0x3FFFFFFF;
            }
            if (!visited.add(sum)) {
                result.add(s.substring(i, i + 10));
            }

        }
        return new ArrayList<>(result);

    }

    public static void main(String[] args) {

        Solution1 solution1 = new Solution1();
        solution1.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");

        System.out.println("kfkf");
    }

}
