package com.example.javacodingjourney.gita;

public class VolatileDemo {

    private static volatile  boolean stopRequested = false;  // NOW volatile

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {  // Will see the update!
                i++;
            }
            System.out.println("Background thread stopped. i = " + i);
        });

        backgroundThread.start();

        Thread.sleep(5000);
        stopRequested = true;
        System.out.println("Main thread set stopRequested to true");

        backgroundThread.join();
        // Background thread WILL stop
    }
}
