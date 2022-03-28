package SubSequenceProblem;

public class MaxAlternatingSum {
    /**
     * 最大子序列交替和
     * 测试链接：https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum/
     */

    // 思路：动态规划
    // dpEven[i]表示在0 ~ i上选择元素组成子序列，最后一个选择的元素下标是偶数，可以得到的最大交替和
    // dpOdd[i]表示在0 ~ i上选择元素组成子序列，最后一个选择的元素下标是奇数，可以得到的最大交替和
    // 对于dpEven[i]：对于i位置元素，我们有两种选择，选与不选
    // 不选择该元素时，dpEven[i - 1];选择该元素时，由于i是偶数下标，我们可以加上nums[i]，即：dpOdd[i - 1] + nums[i]
    // 计算dpOdd[i]同理
    // 需要注意的是，最大子序列交替和一定以偶数下标作为最后一个元素
    public long maxAlternatingSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] dpOdd = new long[nums.length];
        long[] dpEven = new long[nums.length];
        dpOdd[0] = 0L;
        dpEven[0] = (long) nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpEven[i] = Math.max(dpEven[i - 1], dpOdd[i - 1] + nums[i]);
            dpOdd[i] = Math.max(dpOdd[i - 1], dpEven[i - 1] - nums[i]);
        }
        return dpEven[nums.length - 1];
    }

    // 使用有限个变量进行空间压缩
    public long maxAlternatingSum1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long odd = 0L;
        long even = (long) nums[0];
        long temp = even;
        for (int i = 1; i < nums.length; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, temp - nums[i]);
            temp = even;
        }
        return even;
    }
}
