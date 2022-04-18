package DataStructureDesign;

import java.util.*;

/**
 * O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 测试链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 */

public class RandomizedCollection {

    private Map<Integer, Integer> indexToValue;
    private Map<Integer, Set<Integer>> valueToIndex;
    private int size;

    public RandomizedCollection() {
        this.indexToValue = new HashMap<>();
        this.valueToIndex = new HashMap<>();
        this.size = 0;
    }

    public boolean insert(int val) {
        boolean ans = true;
        if (valueToIndex.containsKey(val)) {
            Set<Integer> indexSet = valueToIndex.get(val);
            indexSet.add(size);
            ans = false;
        } else {
            Set<Integer> indexSet = new HashSet<>();
            indexSet.add(size);
            valueToIndex.put(val, indexSet);
        }
        indexToValue.put(size, val);
        size++;
        return ans;
    }

    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }
        Set<Integer> indexSet = valueToIndex.get(val);
        int deleteIndex = indexSet.iterator().next();
        int lastValue = indexToValue.get(size - 1);
        Set<Integer> lastValueSet = valueToIndex.get(lastValue);
        indexToValue.put(deleteIndex, lastValue);
        indexToValue.remove(size - 1);
        if (lastValueSet.equals(indexSet)) {
            lastValueSet.remove(size - 1);
        } else {
            lastValueSet.remove(size - 1);
            lastValueSet.add(deleteIndex);
            indexSet.remove(deleteIndex);
        }
        if (indexSet.size() == 0) {
            valueToIndex.remove(val);
        }
        size--;
        return true;
    }

    public int getRandom() {
        int randomIndex = (int) (Math.random() * size);
        return indexToValue.get(randomIndex);
    }
}
