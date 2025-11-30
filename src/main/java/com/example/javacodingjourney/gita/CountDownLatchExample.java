package com.example.javacodingjourney.gita;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        // Rocket launch - wait for 3 systems to be ready
        CountDownLatch countDownLatch = new CountDownLatch(3);

        // Main thread (mission control) waits for all systems to be ready

        new Thread(() -> {
            try {
                System.out.println("Mission Controll : waiting for all 3 systems to be ready");
                countDownLatch.await();
                System.out.println("All Systems are go: Lauch");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();

        String[] systems = {"Fuel System", "Navigation", "Communications"};
        for(String system: systems) {
            new Thread(() -> {
                try {
                    System.out.println(system + " is ready");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(system + " is go for launch");
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        Thread.currentThread().join();


    }
}
