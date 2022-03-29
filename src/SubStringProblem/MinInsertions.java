package SubStringProblem;

public class MinInsertions {
    /**
     * 让字符串成为回文串的最少插入次数
     * 测试链接：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
     */

    // 思路：动态规划
    // 在范围上尝试的模型，dp[i][j]表示让s[i...j]成为回文串的最少插入次数
    // 情况1：s[i] == s[j]时
    // dp[i][j] = dp[i + 1][j - 1]
    // 情况2：s[i] != s[j]时
    // 让s[i+1...j]变为回文串，然后在最右边填s[i];让s[i...j-1]变为回文串，然后在最左边填s[j]
    // 因此dp[i][j] = min(dp[i + 1][j], dp[i][j - 1]) + 1
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            dp[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                if (i == j - 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                } else {
//                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
//                    if (s.charAt(i) == s.charAt(j)) {
//                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
//                    }
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
