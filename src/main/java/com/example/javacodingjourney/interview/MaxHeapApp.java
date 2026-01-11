package com.example.javacodingjourney.interview;

public class MaxHeapApp {

    public static void main(String[] args) {
        MaxHeap max = new MaxHeap(50);
        max.insert(10);
        max.insert(4);
        max.insert(8);
        max.insert(1);
        max.insert(45);
        System.out.println("Max Value ->"+max.peek());

        RamPriorityQueue ramQueue = new RamPriorityQueue();
        ramQueue.enqueue(45);
        ramQueue.enqueue(43);
        ramQueue.enqueue(21);
        System.out.println("process -> "+ ramQueue.dequeue());


    }
}
