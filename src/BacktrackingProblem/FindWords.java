package BacktrackingProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords {
    /**
     * 单词搜索Ⅱ
     * 测试链接：https://leetcode.cn/problems/word-search-ii/
     */
    // 暴力方法求解，遍历每一个单词是否出现在矩阵中，会超时
    public List<String> findWords1(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Set<String> set = new HashSet<>();
        for (String word : words) {
            char[] chs = word.toCharArray();
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(board, chs, 0, i, j, visited)) {
                        set.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    // 深度优先搜索，查找单词是否出现在矩阵中
    public static boolean dfs(char[][] board, char[] word, int i, int x, int y, boolean[][] visited) {
        if (board[x][y] != word[i]) { // 当前位置的字符和单词中的i位置字符不相等
            return false;
        } else if (i == word.length - 1) { // base case
            return true;
        }
        boolean res = false;
        visited[x][y] = true; // 标记位置
        // 分别尝试向上、向下、向左、向右移动
        if (x > 0 && !visited[x - 1][y]) {
            res = dfs(board, word, i + 1, x - 1, y, visited);
            if (res) {
                return res;
            }
        }
        if (x < board.length - 1 && !visited[x + 1][y]) {
            res = dfs(board, word, i + 1, x + 1, y, visited);
            if (res) {
                return res;
            }
        }
        if (y > 0 && !visited[x][y - 1]) {
            res = dfs(board, word, i + 1, x, y - 1, visited);
            if (res) {
                return res;
            }
        }
        if (y < board[0].length - 1 && !visited[x][y + 1]) {
            res = dfs(board, word, i + 1, x, y + 1, visited);
            if (res) {
                return res;
            }
        }
        visited[x][y] = false;
        return res;
    }

    /*====================分割线====================*/
    // 前缀树实现
    public class TrieNode {
        public int end;
        public TrieNode[] nexts;
        public String value; // 该属性伴随end存在，end不为0的结点就会有value

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    public class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            char[] chs = word.toCharArray();
            int index = 0;
            TrieNode cur = root;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new TrieNode();
                }
                cur = cur.nexts[index];
            }
            cur.end++;
            cur.value = word;
        }
    }

    // 方法2，使用前缀树
    public List<String> findWords2(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
//        Set<String> set = new HashSet<>(); // 用于存放结果
        List<String> res = new ArrayList<>(); // 代码优化，不用哈希集合
        Trie trie = new Trie();
        boolean[][] visited = new boolean[m][n];
        for (String word : words) { // 创建前缀树
            trie.insert(word);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对矩阵中的每一个位置进行深度优先搜索
                process(board, trie.root, i, j, res, visited);
            }
        }
        return res;
    }

    // 当前来到矩阵的[x, y]位置，前缀树的cur结点，判断在前缀树中是否含有往下走的路径
    public void process(char[][] board, TrieNode cur, int x, int y, List<String> res, boolean[][] visited) {
        if (cur.nexts[board[x][y] - 'a'] == null) { // 剪枝，当前在前缀树中没有往下走的路径
            return;
        }
        cur = cur.nexts[board[x][y] - 'a'];
        if (cur.end > 0) { // base case, 搜索到了一个单词，将其加入res中
            res.add(cur.value);
            // 优化的关键步骤，以匹配的单词从前缀树中删除，避免寻找相同的单词
            cur.end = 0;
            cur.value = null;
            // 注意当前只是来到了end > 0的结点，但是并未走到前缀树的叶子结点，不能return
        }
        visited[x][y] = true;
        // 在矩阵中能往上下左右走的情况下，尝试这条路径能不能走
        if (x > 0 && !visited[x - 1][y]) {
            process(board, cur, x - 1, y, res, visited);
        }
        if (x < board.length - 1 && !visited[x + 1][y]) {
            process(board, cur, x + 1, y, res, visited);
        }
        if (y > 0 && !visited[x][y - 1]) {
            process(board, cur, x, y - 1, res, visited);
        }
        if (y < board[0].length - 1 && !visited[x][y + 1]) {
            process(board, cur, x, y + 1, res, visited);
        }
        visited[x][y] = false;
    }
}
