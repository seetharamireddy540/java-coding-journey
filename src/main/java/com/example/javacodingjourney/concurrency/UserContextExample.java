package com.example.javacodingjourney.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserContextExample {

    public static void main(String[] args) {

        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 5; i++) {
                final int userId = i;
                executorService.submit(() -> {
                    User user = new User(String.valueOf(userId), "User " + userId);
                    UserContext.setUser(user);

                    processRequest();

                    UserContext.clear();
                });
            }
        }
    }

    public static void processRequest() {
        User user = UserContext.getUser();
        System.out.println("Processing request for user: " + user);

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed processing for " + UserContext.getUser());
    }

    private static class UserContext {
        private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

        public static void setUser(User user) {
            userThreadLocal.set(user);
        }

        public static User getUser() {
            return userThreadLocal.get();
        }

        public static void clear() {
            userThreadLocal.remove();
        }
    }

    private static class User {
        private final String name;
        private final String id;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
