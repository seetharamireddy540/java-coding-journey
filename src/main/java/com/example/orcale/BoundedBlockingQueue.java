package com.example.orcale;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Prompt: Implement a thread-safe BoundedBlockingQueue class with a given capacity. It should support two methods:
 *
 * enqueue(int element): Adds an element to the front of the queue. If the queue is full, the calling thread must be blocked until space becomes available.
 *
 * dequeue(): Returns the element at the rear of the queue. If the queue is empty, the calling thread must be blocked until an element is available.
 *
 * Why it's asked: This is a staple for backend, cloud infrastructure, and distributed systems interviews. It tests a deep, practical understanding of Java's memory model and threading.
 * The Optimal Approach: Avoid relying purely on synchronized blocks. The best Java solutions utilize java.util.concurrent.locks.ReentrantLock combined with two Condition variables (notFull and notEmpty) to efficiently manage thread signaling without busy-waiting.
 */
public class BoundedBlockingQueue {

    private final Queue<Integer> queue;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(int value) throws InterruptedException {
        try {
            lock.lock();
            System.out.println("Adding element -> " + Thread.currentThread().getName());

            while (queue.size() == capacity) {
                System.out.println("Capacity exhausted");
                this.notFull.await();
            }
            queue.add(value);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("Dequeued -> " + Thread.currentThread().getName());
            while (queue.isEmpty()) {
                this.notEmpty.await();
            }
            int item = queue.poll();
            this.notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

}
