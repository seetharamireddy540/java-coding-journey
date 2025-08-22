package com.example.javacodingjourney.ds;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MinHeapApp {

    public static void main(String[] args) {

        PriorityQueue<Integer> minHeap1 = new PriorityQueue<>((a, b) -> b - a);
        minHeap1.add(5);
        minHeap1.add(51);
        minHeap1.add(15);
        minHeap1.add(1);

        System.out.println("Min->" + minHeap1.size());
        System.out.println("Min->" + minHeap1.poll());

        MinHeap minHeap = new MinHeap(20);
        minHeap.insert(20);
        minHeap.insert(12);
        minHeap.insert(1);
        minHeap.insert(13);
        minHeap.insert(3);
        System.out.println(" Peak -> " + minHeap.peek());
        System.out.println(" Min -> " + minHeap.extractMin());
        System.out.println(" Min -> " + minHeap.extractMin());
        System.out.println(" Min -> " + minHeap.extractMin());
        System.out.println(" Min -> " + minHeap.extractMin());
        System.out.println(" Min -> " + minHeap.extractMin());
    }

    public static class MinHeap {

        private final int[] heap;
        private final int capacity;
        private int size;

        public MinHeap(int capacity) {
            this.capacity = capacity;
            this.heap = new int[this.capacity];
            this.size = 0;
        }

        public void swap(int i, int j) {
            int temp = this.heap[i];
            this.heap[i] = this.heap[j];
            this.heap[j] = temp;
        }

        public int parentIndex(int i) {
            return (i - 1) / 2;
        }

        public int leftChildIndex(int i) {
            return 2 * i + 1;
        }

        public int rightChildIndex(int i) {
            return 2 * i + 2;
        }

        public int extractMin() {
            if (this.size == 0) {
                throw new IllegalArgumentException("Empty");
            }
            if (size == 1) {
                this.size--;
                return this.heap[0];
            }
            int min = this.heap[0];
            this.heap[0] = this.heap[this.size - 1];
            this.size--;
            minHeapify(0);
            return min;
        }

        private void minHeapify(int i) {
            int l = leftChildIndex(i);
            int r = rightChildIndex(i);
            int smallest = i;
            if (l < this.size && this.heap[l] < this.heap[smallest]) {
                smallest = l;
            }
            if (r < this.size && this.heap[r] < this.heap[smallest]) {
                smallest = r;
            }
            if (smallest != i) {
                swap(i, smallest);
                minHeapify(smallest);
            }
        }

        public void insert(int element) {
            if (this.size >= this.capacity) {
                throw new IllegalArgumentException("Heap is full");
            }
            this.heap[this.size] = element;
            int current = this.size;
            this.size++;

            while (current > 0 && element < this.heap[parentIndex(current)]) {
                int parent = parentIndex(current);
                swap(current, parent);
                current = parent;
            }
        }

        public int peek() {
            if (this.size > 0) {
                return this.heap[0];
            }
            throw new IllegalArgumentException("Empty heap");
        }
    }

    public Integer findKthLarget(int[] nums, int k) {
        PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }
}
