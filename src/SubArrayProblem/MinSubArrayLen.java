package SubArrayProblem;

public class MinSubArrayLen {
    /**
     * 长度最小的子数组
     * 测试链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     */

    // 思路：滑动窗口
    // L和R维持窗口的左右边界
    // 当sum >= target时，移动左边界；当sum < target时移动右边界
    // res在遍历过程中保存最小值
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int L = 0, R = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (; R < nums.length; R++) {
            sum += nums[R];
            while (sum >= target) {
                res = Math.min(res, R - L + 1);
                sum -= nums[L++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
