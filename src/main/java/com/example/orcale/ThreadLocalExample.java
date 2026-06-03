package com.example.orcale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int finalI1 = i;
            executorService.execute(() -> {
                try {
                    ThreadLocalExample threadLocalExample = new ThreadLocalExample();
                    UserContext.setUser("Ram-" + finalI1);
                    threadLocalExample.processOrder();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    UserContext.removeUser();
                }
            });
        }

        Thread.currentThread().join();

    }

    public void processOrder() {
        System.out.println("Processing order for user " + UserContext.getUser() + "Thread - " + Thread.currentThread().getName());

        this.processPayment();
        this.sendEmail();
        this.sendSMS();
    }

    public void processPayment() {
        System.out.println("Processing payment for user " + UserContext.getUser() + "Thread - " + Thread.currentThread().getName());

    }

    public void sendEmail() {
        System.out.println("Processing Email for user " + UserContext.getUser() + "Thread - " + Thread.currentThread().getName());
    }

    public void sendSMS() {
        System.out.println("Processing SMS for user " + UserContext.getUser() + "Thread - " + Thread.currentThread().getName());
    }

    private static class UserContext {
        private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private static void setUser(String userId) {
            threadLocal.set(userId);
        }

        private static String getUser() {
            String userId = threadLocal.get();
            return userId;
        }

        private static void removeUser() {
            threadLocal.remove();
        }
    }
}
