package BacktrackingProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermuteUnique {
    /**
     * 全排列 II
     * 测试链接：https://leetcode-cn.com/problems/permutations-ii/
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> list = new ArrayList<>();

    // 剪枝策略
    // 对于在index位置上做决策
    // 如果之前已经考虑过当前数，就不要在考虑对它做决策了
    public void dfs(int[] arr, int index) {
        if (index == arr.length) {
            list.clear();
            addElements(list, arr);
            res.add(new ArrayList<>(list));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int k = index; k < arr.length; k++) {
            if (!set.contains(arr[k])) { // index位置上之前没有考虑过arr[k]时，我们才考虑在index位置上放arr[index]
                set.add(arr[k]);
                swap(arr, index, k);
                dfs(arr, index + 1);
                swap(arr, index, k);
            }
        }
    }

    public void addElements(List<Integer> list, int[] nums) {
        for (int num : nums) {
            list.add(num);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        dfs(nums, 0);
        return res;
    }
}
