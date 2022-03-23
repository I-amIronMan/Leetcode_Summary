package SubArrayProblem;

import java.util.HashMap;

public class SubArraySum {
    /**
     * 和为k的子数组
     * 测试链接：https://leetcode-cn.com/problems/subarray-sum-equals-k/
     */

    // 思路：前缀和 + 哈希表
    // 计算每个位置的前缀和，即：prefix[i] = nums[0] + nums[1] +...+ nums[i]
    // 哈希表存储：<前缀和，前缀和的数量>，map.put(0, 1)表示初始化时，前缀和为0的数量为1
    // 当来到i位置时，在哈希表中查找是否有前缀和prefix[i] - k，如果有，说明就有累计和为k的子数组
    // 每计算完一个位置后，更新哈希表
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(prefix[i] - k)) {
                res += map.get(prefix[i] - k);
            }
            map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }
        return res;
    }

    // 优化：前缀和数组使用一个变量代替，状态压缩
    public int subarraySum1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefix = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (map.containsKey(prefix - k)) {
                res += map.get(prefix - k);
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return res;
    }
}
