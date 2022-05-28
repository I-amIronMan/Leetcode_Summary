package BacktrackingProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSubsequences {
    /**
     * 递增子序列
     * 测试链接：https://leetcode.cn/problems/increasing-subsequences/
     */

    public Set<List<Integer>> set;
    public List<Integer> list;

    public void dfs(int[] nums, int i) {
        if (i == nums.length) {
            set.add(new ArrayList<>(subList));
            return;
        }
        dfs(nums, i + 1);
        if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
            list.add(nums[i]);
            dfs(nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // 思路：回溯法
    // 暴力递归，不进行剪枝，使用set去重
    public List<List<Integer>> findSubsequences(int[] nums) {
        set = new HashSet<>();
        list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0);
        for (List<Integer> subList : set) {
            if (subList.size() > 1) {
                res.add(subList);
            }
        }
        return res;
    }

    // 优化版本
    // 使用剪枝，不需要用set去重
    public List<List<Integer>> res;
    public List<Integer> subList;

    public void process(int[] nums, int i) {
        if (i == nums.length) { // base case
            if (subList.size() >= 2) { // 只要长度大于1的子序列
                res.add(new ArrayList<>(subList));
            }
            return;
        }
        // 当前元素和subList中的最后一个元素不相同时，可以不选当前元素
        if (subList.size() == 0 || nums[i] != subList.get(subList.size() - 1)) { // 剪枝
            process(nums, i + 1);
        }
        // 当前元素比subList中最后一个元素大或者等于时，可以选当前元素
        // 但是当前元素要是比subList中的最后一个元素大，就必须要选当前元素
        if (subList.size() == 0 || nums[i] >= subList.get(subList.size() - 1)) { // 剪枝
            subList.add(nums[i]);
            process(nums, i + 1);
            subList.remove(subList.size() - 1);
        }
    }

    public List<List<Integer>> findSubsequences1(int[] nums) {
        subList = new ArrayList<>();
        res = new ArrayList<>();
        process(nums, 0);
        return res;
    }
}
