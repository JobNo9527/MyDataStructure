package MaxHeap.LC215;

import java.util.PriorityQueue; //默认底层实现是个最小堆实现的

public class Solution {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++)
            pq.add(nums[i]);

        for (int i = k; i < nums.length; i++)
            if (!pq.isEmpty() && nums[i] > pq.peek()) {
                pq.remove();    // 默认删除队首元素
                pq.add(nums[i]);
            }

        return pq.peek();
    }
}
