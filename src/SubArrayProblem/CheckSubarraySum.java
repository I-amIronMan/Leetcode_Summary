package SubArrayProblem;

import java.util.HashMap;
import java.util.Map;

public class CheckSubarraySum {
    /**
     * 连续的子数组和
     * 测试连接：https://leetcode.cn/problems/continuous-subarray-sum/
     */

    // 思路1：哈希表+枚举
    // 超时
    // 时间复杂度O(N * sum),sum为数组中元素的和
    public static boolean checkSubarraySum1(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length - 1; i++) { // 有两个连续的0
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int sum = preSum;
            while (sum >= 0) {
                if (map.containsKey(sum - k)) {
                    if (i - map.get(sum - k) > 1) {
                        return true;
                    }
                }
                sum -= k;
            }
            map.put(preSum, i);
        }
        return false;
    }

    // 思路2：哈希表+前缀和
    // 只计算并用哈希表记录前缀和对k取余的余数
    // 如果两个前缀和对应的余数相同，则说明对应的子数组和可以整除k
    // 哈希表记录(前缀和余数, 对应的下标)
    // 注意哈希表没查到remain时记录，查到时不用更新
    public static boolean checkSubarraySum2(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 前缀和为0，结束下标为-1
        int remain = 0;
        for (int i = 0; i < nums.length; i++) {
            remain = (remain + nums[i]) % k; // 计算当前位置的前缀和下标
            if (map.containsKey(remain)) { // 哈希表中查到remain
                if (i - map.get(remain) > 1) { // 查看对应的子数组长度是否大于1
                    return true;
                }
            } else { // 哈希表中未查到remain
                map.put(remain, i);
            }
        }
        return false;
    }
}
