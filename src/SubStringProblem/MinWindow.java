package SubStringProblem;

public class MinWindow {
    /**
     * 最小覆盖子串
     * 测试链接：https://leetcode-cn.com/problems/minimum-window-substring/
     */

    // 思路：滑动窗口 + 欠债表
    // 将t字符串统计一个词频表作为欠债表使用
    // all代表s欠t的总字符数量，当all = 0时，说明s换完了所有字符，当前窗口内的子串满足题目条件
    // 窗口右边界r右移，表示s给t还款
    // 窗口左边界l右移，表示s向t借款
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        int[] map = new int[256]; // 使用数组代替哈希表作为词频表使用
        char[] tStr = t.toCharArray();
        char[] sStr = s.toCharArray();
        for (char ch : tStr) {
            map[ch]++;
        }
        int all = tStr.length;
        int l = 0;
        String res = null;
        for (int r = 0; r < s.length(); r++) {
            if (map[sStr[r]] > 0) { // 说明这是一次有效还款，all减少一个
                all--;
            }
            map[sStr[r]]--;
            while (all == 0) { // 当all等于0时说明当前窗口所包含的字符串满足条件
                if (res == null || res.length() > r - l + 1) { // 更新res
                    res = s.substring(l, r + 1);
                }
                if (map[sStr[l]] == 0) { // 说明借款借的是t需要的字符，借回去后s右欠t字符了，all增加一个
                    all++;
                }
                map[sStr[l++]]++;
            }
        }
        return res == null ? "" : res; // 最终res为null说明没有找到满足条件的子串
    }
}
