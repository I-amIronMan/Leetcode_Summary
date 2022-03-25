package SubStringProblem;

public class CountSubstrings {
    /**
     * 回文子串
     * 测试链接：https://leetcode-cn.com/problems/palindromic-substrings/
     */

    // 思路：Manacher算法
    // 需要注意的是，在统计回文串的数量时，以i为中心点的最大回文半径为pArr[i]
    // 那么必须以i为中心点的回文串数量为pArr[i]/2个
    // 举个例子
    // #a#a#a#
    // 0123456
    // pArr[0] = 1，回文串有0个；pArr[1] = 2，回文串有1个；pArr[2] = 3，回文串有1个；
    // pArr[3] = 4，回文串有2个；pArr[4] = 3，回文串有1个；pArr[5] = 2，回文串有1个；
    // pArr[6] = 1，回文串有0个；总计6个
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chs = manacherString(s);
        int[] pArr = new int[chs.length]; // 回文半径数组
        int R = -1; // 当前扩到的最大回文右边界再往右一个位置
        int C = -1; // 当前扩到的最大回文区域的中心点
        int res = 0;
        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = i < R ? Math.min(R - i, pArr[2 * C - i]) : 1; // 当前至少不用验的区域
            while (i + pArr[i] < pArr.length && i - pArr[i] >= 0) { // 看是否能往外扩，能扩就扩
                if (chs[i + pArr[i]] == chs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) { // 当前最大回文区域已经超过R了，更新R和C
                R = i + pArr[i];
                C = i;
            }
            res += pArr[i] / 2;
        }
        return res;
    }

    public char[] manacherString(String s) {
        char[] res = new char[2 * s.length() + 1];
        char[] chs = s.toCharArray();
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chs[index++];
        }
        return res;
    }
}
