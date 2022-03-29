package SubStringProblem;

public class MinCut {
    /**
     * 分割回文串II
     * 测试链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii/
     */

    // 思路：动态规划
    // 从左往右尝试的模型，dp[i]表示s[i...n-1]能够分割出的最少回文子串个数
    // 计算dp[i]时，遍历i到n-1，若i到j范围上的子串为回文串，那么得到一种分割方案：分割i~j，剩下的部分即为dp[j + 1]
    // 由于需要得到最少的分割次数，所以在遍历过程中取最小的答案
    // 最终dp[0]即为整个字符串分割出的最少回文字符串个数
    // 注意最少分割次数 = 分割出的最少回文字符串个数 - 1
    // 时间复杂度O(N^2)
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 0;
        boolean[][] record = isPalindrome(s);
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (record[i][j]) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0] - 1;
    }

    // 预处理数组，判断字符串s[i...j]是否为回文串
    public boolean[][] isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return new boolean[0][0];
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            dp[j][j] = true;
            for (int i = j - 1; i >= 0; i--) {
                if (i == j - 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
            }
        }
        return dp;
    }
}
