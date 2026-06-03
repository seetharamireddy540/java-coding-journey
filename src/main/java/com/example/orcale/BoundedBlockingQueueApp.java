package com.example.orcale;


public class BoundedBlockingQueueApp {
    public static void main(String[] args) throws InterruptedException {

        BoundedBlockingQueue queue = new BoundedBlockingQueue(10);

        Thread producer = new Thread(() -> {
            int i = 0;
            while(true) {
                try {
                    queue.enqueue(i++);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.setName("producer thread");
        Thread consumer = new Thread(() -> {
            while(true) {
                try {
                    int element = queue.dequeue();
                    System.out.println("Consumed element -> " + element);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        consumer.setName("consumer thread");
        producer.start();
        consumer.start();
        Thread.currentThread().join();
    }
}
