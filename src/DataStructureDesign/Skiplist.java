package DataStructureDesign;

import java.util.ArrayList;

/**
 * 设计跳表
 * 测试链接：https://leetcode-cn.com/problems/design-skiplist/
 */

class SkipListNode {
    public int val;
    public ArrayList<SkipListNode> nexts;

    public SkipListNode() {
        this.nexts = new ArrayList<>();
    }

    public SkipListNode(int val) {
        this.val = val;
        this.nexts = new ArrayList<>();
    }
}

public class Skiplist {
    private static final double PROBABILITY = 0.5;
    private static final int MAX_LEVEL = 31;
    private SkipListNode head;
    private int size;
    private int maxLevel;

    public Skiplist() {
        this.head = new SkipListNode();
        this.head.nexts.add(null);
        this.size = 0;
        this.maxLevel = 0;
    }

    // 在整个跳表中查找小于val的最右结点
    private SkipListNode mostRightLessNodeInTree(int val) {
        int level = maxLevel;
        SkipListNode cur = head;
        while (level >= 0) {
            cur = mostRightLessNodeInLevel(val, cur, level--);
        }
        return cur;
    }

    // 在跳表同一层(level)中查找小于val的最右结点
    private SkipListNode mostRightLessNodeInLevel(int val, SkipListNode cur, int level) {
        SkipListNode next = cur.nexts.get(level);
        while (next != null && next.val < val) {
            cur = next;
            next = cur.nexts.get(level);
        }
        return cur;
    }

    public void add(int num) {
        size++;
        int newNodeLevel = 0;
        while (Math.random() < PROBABILITY) {
            newNodeLevel++;
            if (newNodeLevel == MAX_LEVEL) {
                break;
            }
        }
        while (newNodeLevel > maxLevel) {
            head.nexts.add(null);
            maxLevel++;
        }
        SkipListNode newNode = new SkipListNode(num);
        for (int i = 0; i <= newNodeLevel; i++) {
            newNode.nexts.add(null);
        }
        int level = maxLevel;
        SkipListNode pre = head;
        while (level >= 0) {
            pre = mostRightLessNodeInLevel(num, pre, level);
            if (level <= newNodeLevel) {
                newNode.nexts.set(level, pre.nexts.get(level));
                pre.nexts.set(level, newNode);
            }
            level--;
        }
    }

    public boolean search(int target) {
        SkipListNode less = mostRightLessNodeInTree(target);
        SkipListNode next = less.nexts.get(0);
        return next != null && next.val == target;
    }

    public boolean erase(int num) {
        if (!search(num)) {
            return false;
        }
        size--;
        int level = maxLevel;
        SkipListNode pre = head;
        while (level >= 0) {
            pre = mostRightLessNodeInLevel(num, pre, level);
            SkipListNode next = pre.nexts.get(level);
            if (next != null && next.val == num) {
                pre.nexts.set(level, next.nexts.get(level));
            }
            if (level != 0 && pre == head && pre.nexts.get(level) == null) {
                head.nexts.remove(level);
                maxLevel--;
            }
            level--;
        }
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
