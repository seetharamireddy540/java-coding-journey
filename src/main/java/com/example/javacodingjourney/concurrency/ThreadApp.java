package com.example.javacodingjourney.concurrency;

public class ThreadApp {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData(0);

        Task task = new Task(sharedData);
        Thread thread = new Thread(task);
        Thread thread2 = new Thread(task);

        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Final Counter -> " + sharedData.getCounter());
    }

    public static class Task implements Runnable {
        private SharedData sharedData;

        public Task(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        @Override
        public void run() {
            System.out.println("Thread is running" + Thread.currentThread().getName());
            for (int i = 0; i < 100; i++) {
                sharedData.increment();
            }
        }
    }

    public static class SharedData {
        private int counter = 0;

        public SharedData(int counter) {
            this.counter = counter;
        }

        public synchronized void increment() {
            counter++;
        }

        public synchronized int getCounter() {
            return counter;
        }
    }
}
