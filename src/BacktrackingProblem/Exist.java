package BacktrackingProblem;

public class Exist {
    /**
     * 单词搜索
     * 测试链接：https://leetcode.cn/problems/word-search/
     */

    // 回溯法1
    public static boolean exist1(char[][] board, String word) {
        char[] chs = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 对于每一个点作为起点尝试
                res = dfs1(board, chs, 0, i, j, visited);
                if (res) { // 一旦有满足条件的单词直接返回
                    return res;
                }
            }
        }
        return res;
    }

    public static boolean dfs1(char[][] board, char[] word, int i, int x, int y, boolean[][] visited) {
        if (board[x][y] != word[i]) { // 当前位置的字符和单词中的i位置字符不相等
            return false;
        } else if (i == word.length - 1) { // base case
            return true;
        }
        boolean res = false;
        visited[x][y] = true; // 标记位置
        // 分别尝试向上、向下、向左、向右移动
        if (x > 0 && !visited[x - 1][y]) {
            res = dfs1(board, word, i + 1, x - 1, y, visited);
            if (res) {
                return res;
            }
        }
        if (x < board.length - 1 && !visited[x + 1][y]) {
            res = dfs1(board, word, i + 1, x + 1, y, visited);
            if (res) {
                return res;
            }
        }
        if (y > 0 && !visited[x][y - 1]) {
            res = dfs1(board, word, i + 1, x, y - 1, visited);
            if (res) {
                return res;
            }
        }
        if (y < board[0].length - 1 && !visited[x][y + 1]) {
            res = dfs1(board, word, i + 1, x, y + 1, visited);
            if (res) {
                return res;
            }
        }
        visited[x][y] = false;
        return res;
    }

    // 回溯法2
    public static boolean exist2(char[][] board, String word) {
        char[] chs = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 对于每一个点作为起点尝试
                res = dfs2(board, chs, 0, i, j, visited);
                if (res) { // 一旦有满足条件的单词直接返回
                    return res;
                }
            }
        }
        return res;
    }

    public static boolean dfs2(char[][] board, char[] word, int i, int x, int y, boolean[][] visited) {
        if (i == word.length - 1) {
            return word[i] == board[x][y];
        }
        visited[x][y] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean res = false;
        if (word[i] == board[x][y]) {
            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                    if (!visited[newX][newY]) {
                        res = dfs2(board, word, i + 1, newX, newY, visited);
                        if (res) {
                            return res;
                        }
                    }
                }
            }
        }
        visited[x][y] = false;
        return res;
    }
}
