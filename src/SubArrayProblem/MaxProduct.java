package SubArrayProblem;

public class MaxProduct {
    /**
     * 乘积最大子数组
     * 测试链接：https://leetcode-cn.com/problems/maximum-product-subarray/
     */

    // 思路：动态规划
    // 使用两个dp数组。dpMax[i]表示以i结尾的最大乘积子数组，dpMin[i]表示以i位置结尾的最小乘积子数组
    // 由于数组中元素的值有正负，所以状态转移方程如下：
    // dpMax[i] = max(nums[i], nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1])
    // dpMin[i] = min(nums[i], nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1])
    public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = dpMax[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            res = Math.max(dpMax[i], res);
        }
        return res;
    }

    // 优化，空间压缩
    public int maxProduct1(int[] nums) {
        int dpMax = nums[0];
        int dpMin = nums[0];
        int res = dpMax;
        int tmp1 = dpMax;
        int tmp2 = dpMin;
        for (int i = 1; i < nums.length; i++) {
            dpMax = Math.max(nums[i], Math.max(nums[i] * tmp1, nums[i] * tmp2));
            dpMin = Math.min(nums[i], Math.min(nums[i] * tmp1, nums[i] * tmp2));
            tmp1 = dpMax;
            tmp2 = dpMin;
            res = Math.max(dpMax, res);
        }
        return res;
    }
}
