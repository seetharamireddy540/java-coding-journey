package com.example.javacodingjourney.interview;

import java.util.*;

public class PriorityQueueComparison {
    public static void main(String[] args) {
        // MIN HEAP (smallest element first)
        // Uses natural ordering or (a - b)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        // Or simply: new PriorityQueue<>()

        minHeap.addAll(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Min Heap polling order:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");  // 1 2 5 8 9
        }

        // MAX HEAP (largest element first)
        // Uses reverse ordering or (b - a)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // Or: new PriorityQueue<>(Collections.reverseOrder())

        maxHeap.addAll(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("\n\nMax Heap polling order:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");  // 9 8 5 2 1
        }
    }
}
