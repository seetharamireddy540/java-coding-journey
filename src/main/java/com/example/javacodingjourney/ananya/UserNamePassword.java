package com.example.javacodingjourney.ananya;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class UserNamePassword {

    public Map<String, ReentrantLock> userNameMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        UserNamePassword userNamePassword = new UserNamePassword();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> userNamePassword.isValidPassword("ram", "dummy=1")).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(() -> userNamePassword.isValidPassword("gita", "dummy=1")).start();
        }

        Thread.currentThread().join();
    }

    public boolean isValidPassword(String userName, String password) {

        ReentrantLock lock = userNameMap.computeIfAbsent(userName, (key) -> new ReentrantLock());
        lock.lock();
        try {
                String actualHash = actualHash(userName, password);
                String comutedHash = passwordHash(userName, password);
                return actualHash.equals(comutedHash);

        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }

    }

    public String actualHash(String userName, String password) {
        return "dummyahsh";
    }

    public String passwordHash(String userName, String password) throws InterruptedException {
        // Computed Hash
        System.out.println("I ma processing for usernmae=" + userName + " Thread -> " +Thread.currentThread().getName());
        return "dummyahsh";
    }
}
