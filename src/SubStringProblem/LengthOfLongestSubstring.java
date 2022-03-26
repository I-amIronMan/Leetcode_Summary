package SubStringProblem;

import java.util.Arrays;

public class LengthOfLongestSubstring {
    /**
     * 无重复字符的最长子串
     * 测试链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */

    // 思路1：动态规划
    // dp[i]表示以i位置结尾的最长无重复子串的长度
    // base case:dp[0] = 1
    // 两个约束
    // 1. dp[i]的长度不能超过dp[i- 1] + 1
    // 2. 设上一次出现i位置上字符的位置为k，那么dp[i]的长度不能超过i - k
    // 使用一个map记录上次出现s.charAt(i)的位置
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int cur = 1;
        map[s.charAt(0)] = 0;
        int res = cur;
        for (int i = 1; i < s.length(); i++) {
            if (map[s.charAt(i)] == -1) {
                cur = cur + 1;
            } else {
                cur = Math.min(cur + 1, i - map[s.charAt(i)]);
            }
            map[s.charAt(i)] = i;
            res = Math.max(res, cur);
        }
        return res;
    }

    // 思路2：滑动窗口
    // 使用一个窗口维持当前的一个无重复字符的子串
    // 枚举左边界，在每一个左边界，右边界尽可能的往右边扩
    // 注意这里的窗口左右边界是左闭右开，即[l, r)，窗口内字符个数为r - l
    // 使用一个map记录窗口内有哪些字符
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[256];
        int res = 0;
        int r = 0;
        for (int l = 0; l < s.length(); l++) {
            if (l > 0) { // 窗口左边界右移，把不包含在窗口内的字符去掉
                map[s.charAt(l - 1)] = 0;
            }
            while (r < s.length() && map[s.charAt(r)] == 0) { // 右边界尽可能的往右扩
                map[s.charAt(r)] = 1;
                r++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
