package UnionFindSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RemoveStones {
    /**
     * 移除最多的同行或同列石头
     * 测试链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
     */
    public class UnionFind {
        private Map<String, String> fatherMap;
        private Map<String, Integer> rankMap;

        public UnionFind(int[][] m) {
            this.fatherMap = new HashMap<>();
            this.rankMap = new HashMap<>();
            for (int i = 0; i < m.length; i++) {
                String key = String.valueOf(m[i][0] + "_" + m[i][1]); // 并查集的结点使用String将r和c拼接起来作为唯一标识
                fatherMap.put(key, key);
                rankMap.put(key, 1);
            }
        }

        private String find(String cur) {
            String father = fatherMap.get(cur);
            if (!father.equals(cur)) {
                father = find(father);
            }
            fatherMap.put(cur, father);
            return father;
        }

        public void union(int r1, int c1, int r2, int c2) {
            String s1 = String.valueOf(r1 + "_" + c1);
            String s2 = String.valueOf(r2 + "_" + c2);
            if (fatherMap.containsKey(s1) && fatherMap.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    String big = rankMap.get(f1) > rankMap.get(f2) ? f1 : f2;
                    String small = big.equals(f1) ? f2 : f1;
                    fatherMap.put(small, big);
                    rankMap.put(big, rankMap.get(small) + rankMap.get(big));
                    rankMap.remove(small);
                }
            }
        }

        public int size() {
            return rankMap.size();
        }
    }

    public int removeStones(int[][] stones) {
        int N = stones.length;
        int row = 0;
        int col = 0;
        for (int i = 0; i < N; i++) {
            row = Math.max(row, stones[i][0]);
            col = Math.max(col, stones[i][1]);
        }
        int[] rowFirst = new int[row + 1];
        Arrays.fill(rowFirst, -1);
        int[] colFirst = new int[col + 1];
        Arrays.fill(colFirst, -1);
        UnionFind uf = new UnionFind(stones);
        for (int i = 0; i < N; i++) {
            if (rowFirst[stones[i][0]] == -1) {
                rowFirst[stones[i][0]] = i;
            } else {
                int[] pre = stones[rowFirst[stones[i][0]]];
                uf.union(pre[0], pre[1], stones[i][0], stones[i][1]);
            }
            if (colFirst[stones[i][1]] == -1) {
                colFirst[stones[i][1]] = i;
            } else {
                int[] pre = stones[colFirst[stones[i][1]]];
                uf.union(pre[0], pre[1], stones[i][0], stones[i][1]);
            }
        }
        return N - uf.size();
    }
}
