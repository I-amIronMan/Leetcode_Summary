package SubArrayProblem;

import java.util.Stack;

public class MaxSumMinProduct {
    /**
     * 子数组最小乘积的最大值
     * 测试链接：https://leetcode-cn.com/problems/maximum-subarray-min-product/
     */
    public final int mod = 1000000007;

    // 思路：单调栈
    // 对于位置i上的元素，找到左边和右边离它最近的比它小的元素位置L和R
    // 那么以i位置作为最小值的最小乘积为nums[i] * (nums[L + 1] + ... + nums[R - 1])
    // 遍历整个数组，即可得到最终最小乘积的最大值
    public int maxSumMinProduct(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        long[] prefix = new long[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        long res = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                int popIndex = stack.pop();
                res = Math.max(res, nums[popIndex] * (prefix[i - 1] - (stack.isEmpty() ? 0 : prefix[stack.peek()])));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            res = Math.max(res, nums[popIndex] * (prefix[nums.length - 1] - (stack.isEmpty() ? 0 : prefix[stack.peek()])));
        }
        return (int) (res % mod);
    }
}
