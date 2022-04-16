package DataStructureDesign;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /**
     * LRU 缓存
     * 测试链接：https://leetcode-cn.com/problems/lru-cache/
     */

    public static class Node { // 双向链表结点类
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 新加入的结点放在双向链表尾部
    // 访问过的结点移动到结点尾部
    // 最近最少使用的结点一定在链表的头部
    public static class LRU {
        private int capacity; // 最大容量
        private int size; // 当前容量
        private Node head; // 哑头结点
        private Node tail; // 哑尾结点
        private Map<Integer, Node> map; // 根据key快速找到相应的结点

        public LRU(int capacity) { // 初始化
            this.capacity = capacity;
            this.size = 0;
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.map = new HashMap<>();
        }

        private void moveToTail(Node node) { // 将结点移动至尾部
            deleteNode(node);
            addToTail(node);
        }

        private void addToTail(Node node) { // 在尾部添加结点
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        private void deleteNode(Node node) { // 删除指定结点
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) { // 如果存在key，修改结点的value值
                Node node = map.get(key);
                node.value = value;
                moveToTail(node);
            } else { // 不存在key
                if (size == capacity) { // 容量满了，删除链表头部结点
                    if (capacity == 0) {
                        return;
                    }
                    Node node = head.next;
                    if (node != tail) {
                        deleteNode(node);
                        map.remove(node.key);
                    }
                    size--;
                }
                // 在尾部添加新结点
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToTail(newNode);
                size++;
            }
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            moveToTail(node); // 访问过该节点后，将该结点移动至尾部
            return node.value;
        }
    }
}
