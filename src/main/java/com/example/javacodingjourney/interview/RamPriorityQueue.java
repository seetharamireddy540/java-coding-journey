package com.example.javacodingjourney.interview;

public class RamPriorityQueue {

    private MaxHeap maxHeap;

    public RamPriorityQueue() {
        this.maxHeap = new MaxHeap(50);
    }

    public void enqueue(int value) {
        maxHeap.insert(value);
    }

    public int peek() {
        return maxHeap.peek();
    }

    public int dequeue() {
        return maxHeap.extractMax();
    }
}
