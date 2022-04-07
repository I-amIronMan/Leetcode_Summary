package SubSequenceProblem;

import java.util.HashMap;
import java.util.Map;

public class LongestSubsequence {
    /**
     * 最长定差子序列
     * 测试链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/
     */

    // 思路：动态规划 + 哈希表
    // dp[i]表示必须以i位置结尾的最长定差子序列长度
    // 计算dp[i]时，在之前查找arr[i] - difference的dp值
    // 因此需要使用哈希表来存储(arr[i], dp[i])
    // 最终答案为所有dp值中选出最大的
    // 该题可以使用空间压缩
    public int longestSubsequence(int[] arr, int difference) {
        int cur = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            if (map.containsKey(arr[i] - difference)) {
                cur = map.get(arr[i] - difference) + 1;
            } else {
                cur = 1;
            }
            map.put(arr[i], cur);
            res = Math.max(res, cur);
        }
        return res;
    }
}
