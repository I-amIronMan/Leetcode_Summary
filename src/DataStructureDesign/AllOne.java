package DataStructureDesign;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 全 O(1) 的数据结构
 * 测试链接：https://leetcode-cn.com/problems/all-oone-data-structure/
 */

class Node { //定义双向链表结点
    public Set<String> keys; // 存储相同计数的key
    public int count; //计数
    public Node prev;
    public Node next;

    public Node() {
        this.keys = new HashSet<>();
    }

    public Node(String key, int count) {
        this.keys = new HashSet<>();
        keys.add(key);
        this.count = count;
    }

    public void insert(Node node) { // 在当前结点后插入结点
        Node next = this.next;
        node.prev = this;
        node.next = next;
        this.next = node;
        next.prev = node;
    }

    public void remove() { // 删除当前结点
        Node prev = this.prev;
        Node next = this.next;
        prev.next = next;
        next.prev = prev;
    }
}

public class AllOne {
    private Map<String, Node> map; // 根据key快速找到相应的Node
    private Node head; // 双向链表的哑头结点
    private Node tail; // 双向链表的哑尾结点

    public AllOne() {
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (map.containsKey(key)) { // map中有记录
            Node cur = map.get(key);
            Node next = cur.next;
            // key的计数加1，此时分为两种情况
            if (next.count == cur.count + 1) { // 有计数为cur.count+1的结点
                next.keys.add(key); // key存放到下一个结点中
                map.put(key, next); // 更新map
            } else { // 没有计数为cur.count+1的结点
                Node newNode = new Node(key, cur.count + 1); // 创建一个新结点
                cur.insert(newNode); // 插入到链表中
                map.put(key, newNode); // 更新map
            }
            cur.keys.remove(key); // 当前结点移除key的记录
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        } else { // map中没有记录
            // key的计数为1，看链表中有没有计数为1的结点
            if (head.next.count == 1) { // 有计数为1的结点
                head.next.keys.add(key); // 计数为1的结点添加key
                map.put(key, head.next); // 更新map
            } else { // 没有计数为1的结点
                Node newNode = new Node(key, 1); // 创建一个计数为1的结点
                head.insert(newNode);
                map.put(key, newNode); // 更新map
            }
        }
    }

    public void dec(String key) {
        Node cur = map.get(key);
        // 题目保证删除时key一定存在与数据结构中
        // 两种情况，key的计数为1;key的计数大于1
        if (cur.count == 1) {
            map.remove(key); // 直接移除记录
        } else {
            // 根据链表中是否有计数为cur.count-1的结点分类
            if (cur.prev == head || cur.count - cur.prev.count > 1) { // 没有计数为cur.count-1的结点
                Node newNode = new Node(key, cur.count - 1); // 创建一个新结点
                cur.prev.insert(newNode); // 插入到链表中
                map.put(key, newNode); // 更新map
            } else { // 有计数为cur.count-1的结点
                cur.prev.keys.add(key);
                map.put(key, cur.prev); // 更新map
            }
        }
        cur.keys.remove(key); // 当前结点移除key的记录
        if (cur.keys.isEmpty()) {
            cur.remove();
        }
    }

    public String getMaxKey() {
        return head.next == tail ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

}
