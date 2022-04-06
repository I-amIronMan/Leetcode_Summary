package BacktrackingProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    /**
     * 组合总和 II
     * 测试链接：https://leetcode-cn.com/problems/combination-sum-ii/
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> list = new ArrayList<>();

    // 剪枝策略
    // 如果当前数和上一个数相同，上一个数不要，那么当前数也只能选择不要
    // 如果选择要当前数，要保证加上当前数之后的sum <= target
    public void dfs(int[] arr, int sum, int target, int index, boolean flag) {
        if (index == arr.length) { // base case
            if (sum == target) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        dfs(arr, sum, target, index + 1, false); // 不要当前数
        if (!flag && index > 0 && arr[index] == arr[index - 1]) { // 剪枝，如果当前数和上一个数相同，上一个数没要，当前数也不要
            return;
        }
        if (sum + arr[index] <= target) { // 剪枝，只有在加上当前数后不大于target，才能要当前数
            list.add(arr[index]);
            dfs(arr, sum + arr[index], target, index + 1, true);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target, 0, false);
        return res;
    }
}
