package com.example.javacodingjourney.interview;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargetElement {
    // Approach 1: Min Heap of size K
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) { // O (n)
            minHeap.offer(num); // o(long(n)
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest
            }
        }

        return minHeap.peek(); // Top of min heap is kth largest
    }

    // Approach 2: Max Heap (less efficient for large arrays)
    public int findKthLargestMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }

        return maxHeap.peek();
    }
}
