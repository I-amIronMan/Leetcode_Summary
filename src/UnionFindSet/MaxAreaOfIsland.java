package UnionFindSet;

import java.util.HashMap;
import java.util.Map;

public class MaxAreaOfIsland {
    /**
     * 岛屿的最大面积
     * 测试链接：https://leetcode-cn.com/problems/max-area-of-island/
     */
    public static class UnionFind { // 并查集
        private Map<Integer, Integer> fatherMap; // 存放结点的父结点
        private Map<Integer, Integer> rankMap; // 存放父节点的孩子数量

        public UnionFind(int[][] grid) {
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        // 结点使用位置标识，第i行j列的位置表示为i * n + j
                        fatherMap.put(i * n + j, i * n + j);
                        rankMap.put(i * n + j, 1);
                    }
                }
            }
        }

        private int findHead(int i) {
            int f = fatherMap.get(i);
            if (f != i) {
                f = findHead(f);
            }
            fatherMap.put(i, f);
            return f;
        }

        public void union(int a, int b) {
            int af = findHead(a);
            int bf = findHead(b);
            if (af != bf) {
                int big = rankMap.get(af) > rankMap.get(bf) ? af : bf;
                int small = big == af ? bf : af;
                fatherMap.put(small, big);
                rankMap.put(big, rankMap.get(small) + rankMap.get(big));
                rankMap.remove(small);
            }
        }

        public int getMaxIsland() {
            int max = 0;
            for (int num : rankMap.values()) {
                max = Math.max(max, num);
            }
            return max;
        }
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                }
            }
        }
        return uf.getMaxIsland();
    }

}
