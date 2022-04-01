package SubArrayProblem;

public class NumberOfArithmeticSlices {
    /**
     * 等差数列划分
     * 测试链接：https://leetcode-cn.com/problems/arithmetic-slices/
     */

    // 思路：动态规划
    // dp[i]表示必须以i位置结尾的等差子数组的个数
    // dp[0] = dp[1] = 0,因为长度不够
    // 记录一个差值diff
    // 考虑i位置，若nums[i] - nums[i - 1] == diff，说明nums[i]可以和之前的元素构成等差子数组
    // 当前有两种决策，不选择nums[i]，dp[i] = dp[i - 1]
    // 选择nums[i]，需要一个变量count记录可以和之前的元素构成多少个等差子数组
    // 如果nums[i] - nums[i - 1] != diff，更新diff，重置count
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int diff = nums[1] - nums[0];
        int[] dp = new int[nums.length];
        int count = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            if (nums[i] - nums[i - 1] == diff) {
                dp[i] += count++;
            } else {
                diff = nums[i] - nums[i - 1];
                count = 1;
            }
        }
        return dp[dp.length - 1];
    }

    // 动态规划法，空间压缩
    public int numberOfArithmeticSlices1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int diff = nums[1] - nums[0];
        int cur = 0;
        int count = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                cur += count++;
            } else {
                diff = nums[i] - nums[i - 1];
                count = 1;
            }
        }
        return cur;
    }

    // 思路2：滑动窗口
    // 长度为L的等差数组中满足条件的等差子数组有(L - 1) * (L - 2) / 2个 (L >= 3)
    // len为窗口的长度，r为窗口右边界
    public int numberOfArithmeticSlices2(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int preDiff = nums[1] - nums[0];
        int len = 2;
        int res = 0;
        for (int r = 2; r < nums.length; r++) {
            int diff = nums[r] - nums[r - 1];
            if (diff == preDiff) {
                len++;
            } else {
                res += (len - 1) * (len - 2) / 2;
                len = 2;
                preDiff = diff;
            }
        }
        res += (len - 1) * (len - 2) / 2;
        return res;
    }
}
