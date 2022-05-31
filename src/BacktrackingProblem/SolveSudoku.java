package BacktrackingProblem;

public class SolveSudoku {
    /**
     * 解数独
     * 测试链接：https://leetcode.cn/problems/sudoku-solver/
     */

    private char[][] res; // 在回溯法中用于保存生成的数独矩阵

    public void solveSudoku(char[][] board) {
        res = new char[9][9];
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        init(board, row, col, bucket); // 初始化原矩阵中的信息
        process(board, 0, 0, row, col, bucket);
        copyArray(res, board);
    }

    private void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + j / 3; // 计算i行j列的数字是在哪一个桶里
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    // 当前来到(i, j)位置，依次尝试1~9的数字
    private boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) { // base case
            copyArray(board, res); // 生成的矩阵在board中，将它复制到res中
            return true;
        }
        // 寻找下一个位置
        int nexti = j == 8 ? i + 1 : i;
        int nextj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') { // 当前位置已经有数字，不用填
            process(board, nexti, nextj, row, col, bucket);
        } else {
            int bit = 3 * (i / 3) + j / 3; // 找到属于哪一个桶
            for (int num = 1; num <= 9; num++) { // 依次尝试1 ~ 9
                if (!row[i][num] && !col[j][num] && !bucket[bit][num]) { //尝试的数字不能冲突
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bit][num] = true;
                    board[i][j] = (char) (num + '0');
                    // 尝试下一个位置
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bit][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    // 复制二维数组
    private void copyArray(char[][] src, char[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

}
