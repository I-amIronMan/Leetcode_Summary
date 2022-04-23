package SubArrayProblem;

public class LongestMountain {
    /**
     * 数组中的最长山脉
     * 测试链接：https://leetcode-cn.com/problems/longest-mountain-in-array/
     */

    // 思路1：预处理数组
    // right数组用来记录数组中对于每一个位置i，右边比arr[i]小且严格递减的元素个数
    // left数组可以压缩
    // 遍历数组，当left和right[i]都大于0时，说明可以形成山脉数组，长度为left+right[i]+1
    public int longestMountain(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int[] right = new int[arr.length];
        int left = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            left = arr[i] > arr[i - 1] ? left + 1 : 0;
            if (left > 0 && right[i] > 0) {
                res = Math.max(res, left + right[i] + 1);
            }
        }
        return res;
    }

    // 思路2：滑动窗口
    // l指向山脉数组左边界，r指向山脉数组右边界
    // 右边界尽可能向右扩展，无法扩展时，让其变成新的左边界，重新扩展
    public int longestMountain1(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        int l = 0, r = 0;
        int res = 0;
        while (l < n - 2) { // l最大为n - 3，不然无法形成长度至少为3的山脉数组
            r = l + 1; // r从l + 1开始向右扩
            if (arr[l] < arr[l + 1]) { // arr[l]必须小于arr[l + 1]
                while (r < n - 1 && arr[r] < arr[r + 1]) { // 向右找山脉子数组的山峰
                    r++;
                }
                if (r < n - 1 && arr[r] > arr[r + 1]) { // 已找到形成最长山脉子数组的山峰，接下来找能形成最长山脉子数组的右边界
                    while (r < n - 1 && arr[r] > arr[r + 1]) {
                        r++;
                    }
                    res = Math.max(res, r - l + 1); // 更新res
                } else { // arr[r] == arr[r + 1]，无法形成山脉数组
                    r++;
                }
            }
            l = r; // 无法在向右扩展时，更新新的左边界
        }
        return res;
    }
}
