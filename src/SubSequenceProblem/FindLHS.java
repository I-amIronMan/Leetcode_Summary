package SubSequenceProblem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindLHS {
    /**
     * 最长和谐子序列
     * 测试链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence/
     */

    // 思路1：哈希表
    // 使用哈希表存储所有元素的出现次数
    // 遍历哈希表的key
    // 统计key和key+1出现的次数总和
    // 最大的总和即为答案
    // 时间复杂度O(N)，空间复杂度O(N)
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }

    // 思路2：排序+滑动窗口
    // 时间复杂度O(NlogN), 空间复杂度O(1)
    public int findLHS1(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int res = 0;
        for (int r = 0; r < nums.length; r++) {
            while (nums[r] - nums[l] > 1) {
                l++;
            }
            if (nums[r] - nums[l] == 1) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}
