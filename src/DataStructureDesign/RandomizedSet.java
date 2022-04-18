package DataStructureDesign;

import java.util.HashMap;
import java.util.Map;

/**
 * O(1) 时间插入、删除和获取随机元素
 * 测试链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */

public class RandomizedSet {

    private Map<Integer, Integer> indexToValue;
    private Map<Integer, Integer> valueToIndex;

    public RandomizedSet() {
        this.indexToValue = new HashMap<>();
        this.valueToIndex = new HashMap<>();
    }

    public boolean insert(int val) {
        if (!valueToIndex.containsKey(val)) {
            int index = indexToValue.size();
            indexToValue.put(index, val);
            valueToIndex.put(val, index);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (valueToIndex.containsKey(val)) {
            int deleteIndex = valueToIndex.get(val);
            int lastIndex = indexToValue.size() - 1;
            int lastValue = indexToValue.get(lastIndex);
            indexToValue.put(deleteIndex, lastValue);
            valueToIndex.put(lastValue, deleteIndex);
            indexToValue.remove(lastIndex);
            valueToIndex.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int size = indexToValue.size();
        int randomIndex = (int) (Math.random() * size);
        return indexToValue.get(randomIndex);
    }
}
