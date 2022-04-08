package SubSequenceProblem;

import java.util.Arrays;

public class WiggleMaxLength {
    /**
     * 摆动序列
     * 测试链接：https://leetcode-cn.com/problems/wiggle-subsequence/
     */

    // 思路1：动态规划
    // down[i]表示0 ~ i上必须以i位置结尾，nums[i]作山谷的最长摆动序列
    // up[i]表示0 ~ i上必须以i位置结尾，nums[i]作山峰的最长摆动序列
    // 计算down[i]时，遍历0 ~ i-1上所有大于nums[i]的位置
    // 计算up[i]时，遍历0 ~ i-1上所有小于nums[i]的位置
    // 时间复杂度O(n^2)
    public int wiggleMaxLength(int[] nums) {
        int[] down = new int[nums.length];
        int[] up = new int[nums.length];
        Arrays.fill(down, 1);
        Arrays.fill(up, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                } else if (nums[j] < nums[i]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                }
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    // 思路2：动态规划
    // down[i]表示0 ~ i上以某个元素结尾、nums[i]作山谷的最长摆动序列
    // up[i]表示0 ~ i上以某个元素结尾、nums[i]作山峰的最长摆动序列
    // 状态转移方程(z这个状态转移方程有一点难想出来)
    // up[i] = nums[i] <= nums[i−1] ? up[i-1] : max(up[i-1], down[i-1] + 1)
    // down[i] = nums[i] >= nums[i−1] ? down[i-1] : max(down[i-1], up[i-1] + 1)
    // 该题可以使用有限个变量进行空间压缩
    // 时间复杂度O(n)
    public int wiggleMaxLength1(int[] nums) {
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(down, up + 1);
            }
        }
        return Math.max(down, up);
    }

    // 思路3：贪心法
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int prediff = nums[1] - nums[0];
        int res = prediff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prediff <= 0) || (diff < 0 && prediff >= 0)) {
                res++;
                prediff = diff;
            }
        }
        return res;
    }
}
