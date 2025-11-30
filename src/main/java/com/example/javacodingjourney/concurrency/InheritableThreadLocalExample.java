package com.example.javacodingjourney.concurrency;

public class InheritableThreadLocalExample {
    // Regular ThreadLocal - not inherited by child threads
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // InheritableThreadLocal - inherited by child threads
    private static InheritableThreadLocal<String> inheritableThreadLocal =
            new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // Set values in parent thread
        threadLocal.set("Parent ThreadLocal Value");
        inheritableThreadLocal.set("Parent InheritableThreadLocal Value");

        System.out.println("Parent thread:");
        System.out.println("  ThreadLocal: " + threadLocal.get());
        System.out.println("  InheritableThreadLocal: " + inheritableThreadLocal.get());

        // Create child thread
        Thread childThread = new Thread(() -> {
            System.out.println("\nChild thread:");
            System.out.println("  ThreadLocal: " + threadLocal.get()); // null
            System.out.println("  InheritableThreadLocal: " + inheritableThreadLocal.get()); // inherited

            // Child can modify its own copy
            inheritableThreadLocal.set("Modified by child");
            System.out.println("  Modified InheritableThreadLocal: " + inheritableThreadLocal.get());
        });

        childThread.start();

        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Parent's value remains unchanged
        System.out.println("\nParent thread after child execution:");
        System.out.println("  InheritableThreadLocal: " + inheritableThreadLocal.get());
    }
}