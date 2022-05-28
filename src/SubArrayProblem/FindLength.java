package SubArrayProblem;

import java.util.Map;

public class FindLength {
    /**
     * 最长重复子数组
     * 测试链接：https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
     */
    // 思路1：动态规划
    // 类似于求最长公共子串问题
    // 这里使用了空间压缩，额外空间复杂度O(1)
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int res = 0;
        int row = 0, col = n - 1;
        int len = 0;
        while (row < m) {
            int i = row;
            int j = col;
            len = 0;
            while (i < m && j < n) {
                if (nums1[i] != nums2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                res = Math.max(res, len);
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return res;
    }

    // 思路2：滑动窗口
    // 将nums1的每一个位置与nums2的0位置对齐，找出最长重复子数组
    // 将nums2的每一个位置与nums1的0位置对齐，找出最长重复子数组
    // 使用res更新得到最长重复子数组的长度
    public int findLength1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxLen = getMaxLength(nums1, nums2, i, 0, len);
            res = Math.max(res, maxLen);
        }
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxLen = getMaxLength(nums1, nums2, 0, i, len);
            res = Math.max(res, maxLen);
        }
        return res;
    }

    public int getMaxLength(int[] nums1, int[] nums2, int start1, int start2, int len) {
        int count = 0, res = 0;
        for (int i = 0; i < len; i++) {
            if (nums1[start1 + i] == nums2[start2 + i]) {
                count++;
            } else {
                count = 0;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
