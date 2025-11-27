package com.example.javacodingjourney.gita;

import java.util.concurrent.Semaphore;

public class ParkingLot {

    private static final int MAX_CAPACITY = 10;
    private static final Semaphore parkingSpace = new Semaphore(MAX_CAPACITY, true);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Car(parkingSpace).start();
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Car extends Thread {
        private Semaphore parkingSpace;
        public Car(Semaphore parkingSpace) {
            this.parkingSpace = parkingSpace;
        }
        @Override
        public void run() {
            try {
                this.parkingSpace.acquire();
                System.out.println("Car " + Thread.currentThread().getId() + " is parked.");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("Car " + Thread.currentThread().getId() + " is leaving.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.parkingSpace.release();
            }
        }
    }
}
