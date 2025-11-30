package com.example.javacodingjourney.gita;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // 3 friends going to movies together - wait for each other at multiple points
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("🎬 All friends together! Moving to next step...\n");
        });

        String[] friends = {"Alice", "Bob", "Charlie"};

        for (String friend : friends) {
            new Thread(() -> {
                try {
                    // Stage 1: Getting ready at home
                    System.out.println(friend + " getting ready at home...");
                    Thread.sleep((long)(Math.random() * 2000));
                    System.out.println(friend + " ready, waiting at meeting point");
                    barrier.await();

                    // Stage 2: Buying tickets
                    System.out.println(friend + " buying ticket...");
                    Thread.sleep((long)(Math.random() * 2000));
                    System.out.println(friend + " has ticket, waiting for others");
                    barrier.await(); // Barrier reused!

                    // Stage 3: Watching movie
                    System.out.println(friend + " watching movie 🍿");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}