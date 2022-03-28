package SubStringProblem;

public class ShortestPalindrome {
    /**
     * 最短回文串
     * 测试链接：https://leetcode-cn.com/problems/shortest-palindrome/
     */

    // 思路：Manacher算法
    // 该题题意就是找到最长的回文前缀子串
    // Manacher算法可以获取一个回文半径数组，找到每一个位置的最大回文半径
    // 在回文半径数组中找到这样一个位置index
    // index要尽可能的大；index位置扩展的最长回文子串的左边界必须是0位置
    // s[0, 2*index]是s的最长回文前缀子串，那么s[2*index+1, s.length() - 1](逆序，去#)是需要在头部添加的最少字符
    public String shortestPalindrome(String s) {
        char[] chs = manacherString(s); //用于Manacher算法处理的字符数组
        int[] pArr = getLPS(s); // 处理后的字符串的最大回文半径数组
        int index = 0;
        for (int i = 0; i < pArr.length; i++) {
            // 最大回文半径 = 该处的索引 + 1，说明左边界是0位置
            // 遍历数组，找到最大的
            if (pArr[i] == i + 1) {
                index = i;
            }
        }
        String s1 = "";
        for (int i = 2 * index + 1; i < chs.length; i++) {
            if (chs[i] != '#') { // 不要#
                s1 = chs[i] + s1; // 逆序
            }
        }
        return s1 + s;
    }

    // 将字符串处理为用于Manacher算法处理的字符串
    public char[] manacherString(String s) {
        char[] chs = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '#' : chs[index++];
        }
        return res;
    }

    // 获取最长回文半径数组
    public int[] getLPS(String s) {
        char[] chs = manacherString(s);
        int[] pArr = new int[chs.length];
        int R = -1;
        int C = -1;
        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = i < R ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i + pArr[i] < chs.length && i - pArr[i] >= 0) {
                if (chs[i + pArr[i]] == chs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
        }
        return pArr;
    }
}
