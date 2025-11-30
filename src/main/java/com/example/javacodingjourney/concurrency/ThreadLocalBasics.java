package com.example.javacodingjourney.concurrency;

public class ThreadLocalBasics {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Integer> threadLocalInt = new ThreadLocal<>();
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread 1");
            threadLocalInt.set(1);
            System.out.println("Thread 1: " + threadLocal.get());
            System.out.println("Thread 1: " + threadLocalInt.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1: " + threadLocal.get());
            System.out.println("Thread 1: " + threadLocalInt.get());

        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
