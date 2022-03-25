package SubArrayProblem;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarray {
    /**
     * 和至少为 K 的最短子数组
     * 测试链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
     */

    // 思路：前缀和 + 滑动窗口(单调队列维持)
    // 该题可类比滑动窗口的最大值那一题
    // 首先计算出一个前缀和数组，prefix[i] = nums[1] + nums[2] + ... + nums[i - 1]
    // 双端队列中的元素维持队首到队尾单调递增(队列中的下标对应在数组中的元素)
    // 设窗口当前维持的窗口为[l, r)，窗口维持的子数组和为prefix[r] - prefix[l]
    // 当prefix[r] - prefix[l] >= k时，即：prefix[r] >= k + prefix[l]时，当前窗口是满足题意的子数组，更新res
    public int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        long[] prefix = new long[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + (long) nums[i - 1];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int r = 0; r < prefix.length; r++) {
            while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[r]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && prefix[deque.peekFirst()] + k <= prefix[r]) {
                res = Math.min(res, r - deque.pollFirst());
            }
            deque.addLast(r);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    /**
     * 相关解释：
     * 1.为什么队列里的元素要维持单调递增？
     * 设当前队列中元素为...l1, l2...，当前r想要入队，且prefix[l1] >= prefix[l2]。
     * 假设prefix[l1] <= prefix[r] - k，那么必有prefix[l2] <= prefix[r] - k，
     * 我们知道l1 < l2，那么r - l1 > r - l2，我们没有考虑l1的必要了，它不可能成为最终答案，因此在队列中可以直接把l1弹出。
     * 2.为什么队首元素在更新res后可以直接弹出?
     * 设队列中元素为l1, l2...，当前r1想要入队，假设prefix[l1] <= prefix[r1] - k，
     * 那么l1作为窗口左边界可以更新res，如果后续l2也满足条件，那么可以获取更小的res，l2就没有用了；
     * 如果后续有r2也满足条件，但r2 - l1 > r1 - l1，不会去更新res，所以也不需要l2了。
     */
}
