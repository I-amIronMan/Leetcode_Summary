package SubArrayProblem;

public class MaxTurbulenceSize {
    /**
     * 最长湍流子数组
     * 测试链接：https://leetcode-cn.com/problems/longest-turbulent-subarray/
     */

    // 思路1：动态规划
    // down[i]表示以i结尾且arr[i] < arr[i - 1]的最长湍流子数组长度
    // up[i]表示以i结尾且arr[i] > arr[i - 1]的最长湍流子数组长度
    // 状态转移方程
    // down[i] = arr[i] < arr[i - 1] ? up[i] + 1 : 1
    // up[i] = arr[i] > arr[i - 1] ? down[i] + 1 : 1
    // 该题可以使用有限个变量进行状态压缩
    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int down = 1, up = 1;
        int res = 1;
        int tmp = down;
        for (int i = 1; i < arr.length; i++) {
            down = arr[i] < arr[i - 1] ? up + 1 : 1;
            up = arr[i] > arr[i - 1] ? tmp + 1 : 1;
            tmp = down;
            res = Math.max(res, Math.max(down, up));
        }
        return res;
    }

    // 思路2：滑动窗口
    // 窗口内维持一个湍流子数组
    // 当arr[r] > arr[r - 1] && arr[r] > arr[r + 1]) || (arr[r] < arr[r - 1] && arr[r] < arr[r + 1]时
    // 可以右边界右移扩大湍流子数组长度，否则令l = r
    // 特殊情况：当l == r时，如果arr[l] == arr[l + 1]，l和r同时右移，否则r右移
    public int maxTurbulenceSize1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int l = 0, r = 0;
        int res = 1;
        while (r < arr.length - 1) {
            if (l == r) {
                if (arr[l] == arr[l + 1]) {
                    l++;
                }
                r++;
            } else {
                if ((arr[r] > arr[r - 1] && arr[r] > arr[r + 1]) || (arr[r] < arr[r - 1] && arr[r] < arr[r + 1])) {
                    r++;
                } else {
                    l = r;
                }
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
