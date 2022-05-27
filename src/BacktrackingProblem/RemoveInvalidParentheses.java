package BacktrackingProblem;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {
    /**
     * 删除无效的括号
     * 测试链接：https://leetcode.cn/problems/remove-invalid-parentheses/
     */
    // 主体流程：
    // 从左往右遍历，删除多余的右括号
    // 从右往左遍历，删除多余的左括号
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    // checkIndex为找到的第一个不合法的)
    // deleteIndex为删除的多余的)的位置
    public void dfs(String s, List<String> res, int checkIndex, int deleteIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) { // 使用count记录，找到第一个不合法的位置
            if (s.charAt(i) == par[0]) {
                count++;
            } else if (s.charAt(i) == par[1]) {
                count--;
            }
            if (count < 0) { // 遇到了不合法的位置，即多余的(
                for (int j = deleteIndex; j <= i; j++) { // 在之前搜索可以删除的位置
                    // deleteIndex位置就是)，或者遇到了)，但是前面不是)，就可以删
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        dfs(s.substring(0, j) + s.substring(j + 1), res, i, j, par);
                    }
                }
                // 这个循环跑完就已经找到了一个合法的子串(每一个右括号都有左括号与其对应)
                return;
            }
        }
        // 翻转字符串，删除多余的左括号
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') { // 前面递归跑完了，已经删除了多余的右括号，该删除多余的左括号了
            dfs(reversed, res, 0, 0, new char[]{')', '('});
        } else { // 多余的左右括号都删除了，加入到res中
            res.add(reversed);
        }
    }
}
