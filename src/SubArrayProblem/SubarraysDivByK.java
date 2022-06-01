package SubArrayProblem;

import java.util.HashMap;
import java.util.Map;

public class SubarraysDivByK {
    /**
     * 和可被 K 整除的子数组
     * 测试链接：https://leetcode.cn/problems/subarray-sums-divisible-by-k/
     */

    // 思路：前缀和 + 哈希表
    public int subarraysDivByK(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int remain = 0;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
//            remain = (preSum % k + k) % k; // 注意此处取模的方式
            remain = Math.floorMod(preSum, k);
            if (map.containsKey(remain)) {
                res += map.get(remain);
            }
            map.put(remain, map.getOrDefault(remain, 0) + 1);
        }
        return res;
    }

}
