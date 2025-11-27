package com.example.javacodingjourney.threads;

public class ThreadExample {

    public static void main(String[] args) {
        // Shred objects
        MyCounter counter = new MyCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getCounter());
    }

    /**
     * Not thread safer
     */
    public static class MyCounter {

        private int counter;

        public void increment() {
            synchronized (this) {
                counter++;
            }
        }

        public int getCounter() {
            return counter;
        }
    }
}
