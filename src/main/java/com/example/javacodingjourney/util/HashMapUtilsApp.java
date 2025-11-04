package com.example.javacodingjourney.util;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapUtilsApp {
    public static void main(String[] args) {

        String text = "Hello world how are you Hello world";
        Map<String, Integer> counter = new HashMap<>();
        for (String word : text.split(" ")){
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        String grade = "A";
        String student ="Ram";
        String student2= "Gita";
        String gradeB = "B";
        Map<String, List<String>> grades = new HashMap<>();

        if (!grades.containsKey(grade)){
            grades.put(grade, new ArrayList<>());
        }

        if (grades.containsKey(grade)){
            List<String> students = grades.get(grade);
            students.add(student);
        }
        grades.computeIfAbsent(grade, s -> new ArrayList<>()).add(student);
        // Example 1: Shopping Cart Price Update
        Map<String, Double> shoppingCart = new HashMap<>();
        shoppingCart.put("Laptop", 999.99);
        shoppingCart.put("Mouse", 25.50);
        shoppingCart.put("Keyboard", 75.00);

        // Apply 10% discount only to existing items
        shoppingCart.computeIfPresent("Laptop", (key, price) -> price * 0.9);
        shoppingCart.computeIfPresent("Monitor", (key, price) -> price * 0.9); // No effect

    }

    @Builder
    @Getter
    public static class UserSession {
        private String username;
        private String email;
        private String userId;
        private String sessionToken;
        private long creationTime;
        private long expirationTime;

    }
}
