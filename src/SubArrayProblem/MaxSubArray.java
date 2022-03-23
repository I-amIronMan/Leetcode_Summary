package SubArrayProblem;

public class MaxSubArray {
    /**
     * 子数组的最大累计和
     * 测试链接：https://leetcode-cn.com/problems/maximum-subarray/
     */

    // 思路：动态规划
    // dp[i]表示必须以i位置结尾的子数组最大累计和
    // 状态转移方程：dp[i] = max(nums[i], dp[i - 1] + nums[i])
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int cur = nums[0];
        int max = cur;
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], nums[i] + cur);
            max = Math.max(max, cur);
        }
        return max;
    }
}
