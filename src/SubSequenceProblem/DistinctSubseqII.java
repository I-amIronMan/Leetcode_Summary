package SubSequenceProblem;

import java.util.Arrays;

public class DistinctSubseqII {
    /**
     * 不同的子序列 II
     * 测试链接：https://leetcode-cn.com/problems/distinct-subsequences-ii/
     */
    public final int mod = 1000_000_007;

    // 思路1：动态规划
    // dp[i]表示s[0...i]中不同子序列的个数(含空串)
    // 对于i位置的字符，有两种决策
    // 不选择s[i]，即为dp[i - 1]
    // 选择s[i]，不考虑重复的子序列，也为dp[i - 1]
    // 由于含重复字符，所以要减去重复的子序列的个数
    // 重复的子序列就是上一次增加s[i]之前的子序列集合
    // 因此，dp[i] = 2 * dp[i - 1] - dp[map[s.charAt(i)] - 1]
    // 最后结果包含空串，记得减1
    public int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = " " + s;
        int[] dp = new int[s.length()];
        int[] map = new int[256];
        Arrays.fill(map, -1);
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] * 2 % mod;
            if (map[s.charAt(i)] != -1) {
                dp[i] -= dp[map[s.charAt(i)] - 1];
            }
            map[s.charAt(i)] = i;
            dp[i] %= mod;
        }
        dp[s.length() - 1]--;
        if (dp[s.length() - 1] < 0) {
            dp[s.length() - 1] += mod;
        }
        return dp[s.length() - 1];
    }

    // 思路2：
    // map存储每一个字符的newAdd
    // 考虑i位置的字符
    // newAdd表示在当前集合中每个子序列加上s[i]的子序列个数
    // curAll = newAdd + all
    // curAll要减去之前出现过s[i]的newAdd
    // 最后结果包含空串，记得减1
    public int distinctSubseqII1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int all = 1;
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            int newAdd = all;
            int curAll = (all + newAdd) % mod;
            curAll = (curAll - map[s.charAt(i)] + mod) % mod; // 防止减的结果为负数
            map[s.charAt(i)] = newAdd;
            all = curAll;
        }
        return all - 1;
    }
}
