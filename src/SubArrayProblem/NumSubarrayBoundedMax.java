package SubArrayProblem;

import java.util.Stack;

public class NumSubarrayBoundedMax {
    /**
     * 区间子数组个数
     * 测试链接：https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum/
     */

    // 思路：单调栈
    // 对于位置i，找到左右两边离它最近比它大的元素位置L和R
    // 判断nums[i]是否属于[left, right]
    // 如果在，那么以它作为最大值的子数组有(R - i) * (i - L)个
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int popIndex = stack.pop();
                if (nums[popIndex] >= left && nums[popIndex] <= right) {
                    res += (i - popIndex) * (popIndex - (stack.isEmpty() ? -1 : stack.peek()));
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            if (nums[popIndex] >= left && nums[popIndex] <= right) {
                res += (nums.length - popIndex) * (popIndex - (stack.isEmpty() ? -1 : stack.peek()));
            }
        }
        return res;
    }
}
