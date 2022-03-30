package SubSequenceProblem;

import java.util.Arrays;

public class LengthOfLIS {
    /**
     * 最长递增子序列
     * 测试链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
     */

    // 思路：动态规划 + 二分查找
    // ends[i]表示所有长度为i+1的递增子序列中的最小结尾
    // 数组中的值全部初始化为MAX_VALUE，值为MAX_VALUE的区域为无效区域
    // 遍历数组，来到i位置时，在ends数组的有效区域二分查找>=nums[i]的最左位置
    // 若在有效区域查找到相应位置index，更新ends[index]的值
    // 否则，扩充有效区域，更新ends数组的值
    // 最终答案为ends数组有效区域部分的长度
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] ends = new int[nums.length];
        Arrays.fill(ends, Integer.MAX_VALUE);
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (ends[m] >= nums[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            res = Math.max(res, l + 1);
        }
        return res;
    }
}
