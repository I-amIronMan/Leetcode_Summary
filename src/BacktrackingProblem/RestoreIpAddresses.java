package BacktrackingProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class RestoreIpAddresses {
    /**
     * 复原IP地址
     * 测试链接：https://leetcode.cn/problems/restore-ip-addresses/
     */
    public List<String> res;

    // 当前来到start位置
    // 当前处理的是ip地址的第id段
    // 从start开始往后尝试，得到所有的可能结果
    public void dfs(String s, int start, int id, int[] segments) {
        if (id == 4) { // base case，ip地址四段已经处理完了
            if (start == s.length()) { // start来到末尾，字符串也刚好处理完了
                insert(segments); // 将结果加入到res中
            }
            return;
        }
        if (start == s.length()) { // 剪枝，当前ip地址四段还没有处理完，但是已经来到结尾位置，不可能拼出完整的ip地址
            return;
        }
        if (s.charAt(start) == '0') { // 当前压中的位置刚好是0，由于不能包含前导0，所以当前的0只能独自成ip地址的一段
            segments[id] = 0;
            dfs(s, start + 1, id + 1, segments);
        }
        // 其它情况
        int addr = 0;
        // 从start开始往后走，计算第id段可能是哪些数字
        for (int end = start; end < s.length(); end++) {
            addr = addr * 10 + s.charAt(end) - '0';
            if (addr > 0 && addr <= 255) { // 只要addr大于0小于256，就可以将其作为ip地址第id段，然后往下递归
                segments[id] = addr;
                dfs(s, end + 1, id + 1, segments);
            } else { // addr不符合ip地址要求，end往后走也不会符合要求，退出循环
                break;
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        int[] segments = new int[4];
        dfs(s, 0, 0, segments);
        return res;
    }

    // 将递归得到的结果拼成字符串加入res
    public void insert(int[] arr) {
        StringJoiner sj = new StringJoiner(".");
        for (int num : arr) {
            sj.add(String.valueOf(num));
        }
        res.add(sj.toString());
    }
}
