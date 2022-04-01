package SubSequenceProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfArithmeticSlices {
    /**
     * 等差数列划分 II - 子序列
     * 测试链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/
     */

    // 思路：动态规划 + 哈希表
    // dp[i]表示0 ~ i范围上，必须以nums[i]结尾的子序列中，长度>=2的等差子序列数量
    // 在每一个dp[i]中设置一个哈希表
    // 哈希表key代表差值，value表示以key作为差值，长度>=2的等差子序列数量
    // 来到i位置时，遍历之前的每一个位置j
    // 在j位置查找差值为diff(nums[i] - nums[j])的等差子序列数量count
    // 那么i位置差值为diff(nums[i] - nums[j])的等差子序列数量为count + 1
    // 以i位置结尾长度大于2且差值为diff的等差子序列的数量为count，因此ans需要加一个count
    // 由于之前可能有相等元素，因此重新设置dp[i]的map中diff的value应该为dp.get(i).getOrDefault((int) diff, 0) + count + 1
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Map<Integer, Integer>> dp = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            dp.add(new HashMap<>());
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long) nums[i] - (long) nums[j];
                if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) { // 差值可能会越界，越界的diff不需要考虑
                    continue;
                }
                int count = dp.get(j).getOrDefault((int) diff, 0);
                ans += count;
                dp.get(i).put((int) diff, dp.get(i).getOrDefault((int) diff, 0) + count + 1);
            }
        }
        return ans;
    }
}
