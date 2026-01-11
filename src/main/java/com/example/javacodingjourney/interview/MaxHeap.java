package com.example.javacodingjourney.interview;

public class MaxHeap {

    private int size;
    private final int[] heap;
    private final int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public int extractMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    private void heapifyDown(int i) {
        int largest = i;
        if (hasLeftChild(i) && heap[getLeftChildIndex(i)] > heap[largest]) {
            largest = getLeftChildIndex(i);
        }
        if (hasRightChild(i) && heap[getRightChildIndex(i)] > heap[largest]) {
            largest = getRightChildIndex(i);
        }
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }

    public void insert(int value) {
        if (size == capacity) {
            throw new RuntimeException("Heap is full");
        }
        int currentIndex = size;
        heap[size] = value;
        size++;
        heapifyUp(currentIndex);
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }
    private void heapifyUp(int index) {
        if (hasParent(index) && heap[getParentIndex(index)] < heap[index]) {
            swap(getParentIndex(index), index);
            heapifyUp(getParentIndex(index));
        }
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getLeftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
