package SubArrayProblem;

public class NumberOfSubarrays {
    /**
     * 统计「优美子数组」
     * 测试链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays/
     */

    // 思路：前缀和 + 哈希表
    // 使用一个前缀数组pre，记录0 ~ i位置上奇数的个数。
    // 使用一个哈希表map记录pre[i]出现的次数
    // 当来到位置i时，寻找符合条件的j，使得pre[j - 1] = pre[i] - k
    // 即在map里查找pre[i] - k出现的次数，就是必须以i结尾的优美子数组的个数
    // map也可以这样理解，map[key] = value表示前缀奇数个数为key的下标位置有value个
    public int numberOfSubarrays(int[] nums, int k) {
        int[] pre = new int[nums.length];
        int[] map = new int[nums.length + 1]; // 由于key的取值在0 ~ nums.length，哈希表可以用数组代替
        pre[0] = nums[0] & 1;
        map[0] = 1;
        int res = 0;
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + (nums[i] & 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (pre[i] >= k) {
                res += map[pre[i] - k];
            }
            map[pre[i]]++;
        }
        return res;
    }

    // 前缀数组可以进行空间压缩
    public int numberOfSubarrays1(int[] nums, int k) {
        int[] map = new int[nums.length + 1];
        int pre = 0;
        int res = 0;
        map[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i] & 1;
            res += pre >= k ? map[pre - k] : 0;
            map[pre]++;
        }
        return res;
    }
}
