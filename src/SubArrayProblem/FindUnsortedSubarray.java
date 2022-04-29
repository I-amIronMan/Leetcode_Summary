package SubArrayProblem;

public class FindUnsortedSubarray {
    /**
     * 最短无序连续子数组
     * 测试链接：https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
     */
    public int findUnsortedSubarray(int[] nums) {
        int left = -1, right = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 找到最长无序子数组的右边界
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
            // 找到最长无序子数组的左边界
            if (min < nums[nums.length - i - 1]) {
                left = nums.length - i - 1;
            } else {
                min = nums[nums.length - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
