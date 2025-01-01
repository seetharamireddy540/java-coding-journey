package com.example.javacodingjourney.concurrency;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Reader Thread
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Read: " + resource.read("key"));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Writer Thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.write("key", "value-" + i);
                System.out.println("Written: value-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        reader.start();
        writer.start();
    }
}

