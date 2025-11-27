package com.example.javacodingjourney.threads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadAnatomy {
    public static void main(String[] args) throws InterruptedException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // Platform Thread = 1:1 mapping with OS thread
        Thread platformThread = new Thread(() -> {
            // Each thread has:
            // 1. Stack memory (default ~1MB on 64-bit JVM)
            int[] localArray = new int[1000]; // Stack allocated

            // 2. Thread ID (unique)
            long threadId = Thread.currentThread().getId();

            // 3. Native OS thread handle
            System.out.println("OS Thread ID: " + threadId);

            // 4. Thread state
            System.out.println("State: " + Thread.currentThread().getState());

            // Heavy resource consumption


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "MyPlatformThread");

        platformThread.start();
        platformThread.join();

        // Show system threads
        System.out.println("\n=== All System Threads ===");
        Thread.getAllStackTraces().keySet().forEach(thread ->
                System.out.println(thread.getName() + " - " + thread.getState())
        );
    }
}