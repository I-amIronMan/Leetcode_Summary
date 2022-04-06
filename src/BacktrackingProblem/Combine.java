package BacktrackingProblem;

import java.util.ArrayList;
import java.util.List;

public class Combine {
    /**
     * 组合
     * 测试链接：https://leetcode-cn.com/problems/combinations/
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> list = new ArrayList<>();

    // 剪枝策略
    // 当前list中的元素个数加上index到n位置上所有元素小于k，无需往下递归
    // 当前list中元素个数大于等于k，不能再选当前元素
    public void dfs(int n, int index, int k) {
        if (list.size() + (n - index + 1) < k) { // 当前list里有size个，剩余index到n位置上所有的元素全选也无法达到k个，剪枝
            return;
        }
        if (index == n) {
            if (list.size() == k) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        dfs(n, index + 1, k);
        if (list.size() < k) { // 如果当前size>=k，那么再选当前元素就超了，剪枝
            list.add(index + 1);
            dfs(n, index + 1, k);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 0, k);
        return res;
    }
}
