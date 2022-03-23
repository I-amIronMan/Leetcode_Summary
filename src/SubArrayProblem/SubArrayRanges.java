package SubArrayProblem;

import java.util.Stack;

public class SubArrayRanges {
    /**
     * 子数组范围和
     * 测试链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges/
     */
    // 寻找每个元素左右两边离它最近比它大的元素位置
    public int[][] getNearestMore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0][0];
        }
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < res.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = arr.length;
        }
        return res;
    }

    // 寻找每个元素左右两边离它最近比它小的元素位置
    public int[][] getNearestLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0][0];
        }
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < res.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = arr.length;
        }
        return res;
    }

    // 思路：单调栈
    // 计算出所有子数组的最大值的和sumMax和所有子数组的最小值的和sumMin
    // 对于每一个数，找到必须以它作为最小值(最大值)的子数组的数量
    // 对于i位置元素，找到左边离它最近的比它大的位置L，右边离它最近的比它大的位置R
    // 那么[L+1, R-1]这一部分一定以nums[i]作为最大值
    // 这一部分必须包含nums[i]的子数组数量有(R-i)*(i-L)个
    public long subArrayRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0L;
        }
        int[][] nearestMore = getNearestMore(nums);
        int[][] nearestLess = getNearestLess(nums);
        long sumMax = 0L;
        long sumMin = 0L;
        for (int i = 0; i < nums.length; i++) {
            sumMax += (long) (nearestMore[i][1] - i) * (i - nearestMore[i][0]) * nums[i];
            sumMin += (long) (nearestLess[i][1] - i) * (i - nearestLess[i][0]) * nums[i];
        }
        return sumMax - sumMin;
    }
}
