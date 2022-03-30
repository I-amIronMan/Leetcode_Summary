package SubSequenceProblem;

import java.util.Arrays;

public class NumDistinct {
    /**
     * 不同的子序列
     * 测试链接：https://leetcode-cn.com/problems/distinct-subsequences/
     */

    // 思路：动态规划
    // dp[i][j]表示在s[0...j]的子序列中t[0...i]出现的次数
    // base case:i = 0时，dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0
    // 第一行状态转移方程：dp[0][j] = dp[0][j - 1] + (s.charAt(j) == t.charAt(0) ? 1 : 0)
    // 当i > j时，s[0...j]的子序列中不可能出现t[0...i]，dp[i][j] = 0
    // 其他情况：
    // t.charAt(i) != s.charAt(j)时，dp[i][j] = dp[i][j - 1]
    // t.charAt(i) == s.charAt(j)时，dp[i][j] = dp[i - 1][j - 1] + (map[s.charAt(j)] == -1 ? 0 : dp[i][map[s.charAt(j)]])
    // map[s.charAt(j)]表示上一次出现字符s.charAt(j)的位置
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return 0;
        }
        int[][] dp = new int[t.length()][s.length()];
        int[] map = new int[256];
        Arrays.fill(map, -1);
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
        for (int j = 1; j < s.length(); j++) {
            dp[0][j] = dp[0][j - 1] + (s.charAt(j) == t.charAt(0) ? 1 : 0);
        }
        for (int i = 1; i < t.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (t.charAt(i) != s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + (map[s.charAt(j)] == -1 ? 0 : dp[i][map[s.charAt(j)]]);
                }
                map[s.charAt(j)] = j;
            }
        }
        return dp[t.length() - 1][s.length() - 1];
    }

    // 另一种动态规划的思路
    // 状态转移方程:
    // dp[i][j] = dp[i - 1][j] + (s.charAt(i) == t.charAt(j) ? dp[i - 1][j - 1] : 0)
    public int numDistinct1(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return 0;
        }
        int[][] dp = new int[s.length()][t.length()];
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] =dp[i - 1][0] + (s.charAt(i) == t.charAt(0) ? 1 : 0);
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j <= Math.min(i, t.length() -1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length() - 1][t.length() - 1];
    }
}
