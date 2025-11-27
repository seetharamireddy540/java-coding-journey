package com.example.javacodingjourney.threads;

public class ThreadBasics {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println("Main Thread: " + mainThread.getName());
        System.out.println("Thread ID: " + mainThread.getId());
        System.out.println("Priority: " + mainThread.getPriority());
        System.out.println("State: " + mainThread.getState());
        System.out.println("Is Daemon: " + mainThread.isDaemon());
        // Creating a new platform thread
        Thread platformThread = new Thread(() -> {
            System.out.println("Platform Thread: " + Thread.currentThread());
            System.out.println("Stack trace depth: " +
                    Thread.currentThread().getStackTrace().length);
        });

        platformThread.start();
    }
}
