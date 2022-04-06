package BacktrackingProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDup {
    /**
     * 子集 II
     * 测试链接：https://leetcode-cn.com/problems/subsets-ii/
     */

    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> list = new ArrayList<>();

    // 剪枝策略
    // 以[1,1,2]为例
    // 对于第一个1和第二个1
    // 我们如果选择第一个1不选择第二个1，或者选择第二个1不选择第一个1
    // 会生成重复的子集
    // 因此这两种情况我们只需要其中一种，对另一种情况进行剪枝
    public void dfs(int[] arr, int index, boolean choosePre) {
        if (index == arr.length) { // base case
            res.add(new ArrayList<>(list));
            return;
        }
        dfs(arr, index + 1, false); // 不要当前数
        if (!choosePre && index > 0 && arr[index] == arr[index - 1]) { // 如果当前数和上一个数相同，并且上一个数没有要，当前数也不要
            return;
        }
        list.add(arr[index]);
        dfs(arr, index + 1, true); // 要当前数
        list.remove(list.size() - 1);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, false);
        return res;
    }
}
