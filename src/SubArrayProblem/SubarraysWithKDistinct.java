package SubArrayProblem;

public class SubarraysWithKDistinct {
    /**
     * K个不同整数的子数组
     * 测试链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers/
     */

    // 思路1：滑动窗口
    // 找出不同整数<=k的子数组数量和不同整数<=k-1的子数组数量
    // 那么不同整数个数恰好为k的子数组数量即为两者相减
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarrayLessK(nums, k) - subarrayLessK(nums, k - 1);
    }

    // 使用一个词频表和一个变量kinds
    // kinds代表当前词频表的整数的种类数
    // kinds>=0时表示词频表里的种类数<=k
    // kinds<0时表示词频表里的种类数>k
    // kinds>=0时滑动右边界
    // kinds<0时滑动左边界
    public int subarrayLessK(int[] nums, int k) {
        if (k == 0 || nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = 0;
        int kinds = k;
        int[] map = new int[nums.length + 1];
        int res = 0;
        for (; r < nums.length; r++) {
            if (map[nums[r]] == 0) {
                kinds--;
            }
            map[nums[r]]++;
            while (kinds < 0) {
                map[nums[l]]--;
                if (map[nums[l]] == 0) {
                    kinds++;
                }
                l++;
            }
            res += r - l + 1;
        }
        return res;
    }

    // 思路2：滑动窗口2.0
    // 使用三个指针，维持两个滑动窗口，两个窗口右边界相同，左边界不同
    // 使用两个词频表，一个存放整数种类为k-1的词频，一个存放整数种类为k的词频
    // 设置两个左边界lLessK, lEqualK
    // 两个变量，kindsLessK和kindsEqualK表示两个窗口的实际整数种类数
    // 设窗口1内的整数种类最多为k，窗口2内的整数种类最多为k-1
    public int subarraysWithKDistinct1(int[] nums, int k) {
        int n = nums.length;
        int[] mapLessK = new int[n + 1];
        int[] mapEqualK = new int[n + 1];
        int lLessK = 0;
        int lEqualK = 0;
        int kindsLessK = 0;
        int kindsEqualK = 0;
        int res = 0;
        for (int r = 0; r < n; r++) {
            // 调整窗口1右边界
            if (mapEqualK[nums[r]] == 0) {
                kindsEqualK++;
            }
            mapEqualK[nums[r]]++;
            // 调整窗口2右边界
            if (mapLessK[nums[r]] == 0) {
                kindsLessK++;
            }
            mapLessK[nums[r]]++;
            // 调整窗口1左边界
            while (kindsEqualK == k + 1) {
                if (mapEqualK[nums[lEqualK]] == 1) {
                    kindsEqualK--;
                }
                mapEqualK[nums[lEqualK]]--;
                lEqualK++;
            }
            // 调整窗口2左边界
            while (kindsLessK == k) {
                if (mapLessK[nums[lLessK]] == 1) {
                    kindsLessK--;
                }
                mapLessK[nums[lLessK]]--;
                lLessK++;
            }
            // 在当前维持的窗口中，整数种类恰好为k的子数组数量为两个窗口左边界的差值
            res += lLessK - lEqualK;
        }
        return res;
    }
}
