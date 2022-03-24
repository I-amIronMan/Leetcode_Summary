package SubArrayProblem;

public class XorQueries {
    /**
     * 子数组异或查询
     * 测试链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
     */

    // 思路：预处理数组
    // 和前缀和差不多，先弄出一个前缀异或数组，xor[i] = xor[0] ^ xor[1] ^ ... ^ xor[i]
    // 计算[L, R]的XOR值：xor[R] ^ xor[L - 1]
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] xor = new int[arr.length];
        xor[0] = arr[0];
        for (int i = 1; i < xor.length; i++) {
            xor[i] = arr[i] ^ xor[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            res[i] = l == 0 ? xor[r] : xor[r] ^ xor[l - 1];
        }
        return res;
    }
}
