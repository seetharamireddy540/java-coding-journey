package com.example.javacodingjourney.interview;

/**
 * A Heap is a complete binary tree that satisfies the heap property:
 * <p>
 * Min Heap: Parent node is always smaller than or equal to its children
 * Max Heap: Parent node is always greater than or equal to its children
 * Key Properties:
 * Complete binary tree (filled left to right)
 * Height: O(log n)
 * Usually implemented using arrays
 * Parent of index i: (i-1)/2
 * Left child of index i: 2*i + 1
 * Right child of index i: 2*i + 2
 *
 *
 * Time Complexity Summary
 * Operation	Time Complexity
 * Insert	O(log n)
 * Extract Min/Max	O(log n)
 * Peek Min/Max	O(1)
 * Build Heap	O(n)
 * Heapify	O(log n)
 * Search	O(n)
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapBasics {
    public static void main(String[] args) {
        // Min Heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        minHeap.offer(1);

        System.out.println("Min Heap:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // 1 2 5 8
        }

        // Max Heap (using reverseOrder)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);

        System.out.println("\nMax Heap:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // 8 5 2 1
        }

        // Custom Comparator Example
        PriorityQueue<int[]> customHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        customHeap.offer(new int[]{3, 100, 90});
        customHeap.offer(new int[]{1, 200, 5});
        customHeap.offer(new int[]{2, 300, 9});

        System.out.println("\nCustom Heap (sorted by first element):");
        while (!customHeap.isEmpty()) {
            int[] arr = customHeap.poll();
            System.out.println(Arrays.toString(arr));
        }
    }
}