package SubArrayProblem;

public class SubarrayProductLessThanK {
    /**
     * 乘积小于K的子数组
     * 测试链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/
     */

    // 思路：滑动窗口
    // 当窗口内元素的乘积小于k时，R右移；当窗口内元素的乘积大于等于k时，L右移
    // 关键在于如何统计子数组的个数
    // 在窗口[L, R]有多少个不重复的乘积小于k的子数组？
    // 要想和之前统计的子数组不重复，那么当前的子数组必须包含nums[R]
    // 因此每次统计时，子数组的数量就是R - L + 1
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int L = 0, R = 0;
        int product = 1;
        int res = 0;
        for (; R < nums.length; R++) {
            product *= nums[R];
            while (product >= k) {
                product /= nums[L++];
            }
            res += R - L + 1;
        }
        return res;
    }
}
