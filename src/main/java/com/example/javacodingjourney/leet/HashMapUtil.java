package com.example.javacodingjourney.leet;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapUtil {
    public static void main(String[] args) {
        Map<String, List<String>> multiMap = new HashMap<>();
        Map<String, AtomicInteger> counterMap = new HashMap<>();
        Map<String, Integer> scoreMap = new HashMap<>();

        // computeIfAbsent - Perfect for multi-value maps
        List<String> values = multiMap.computeIfAbsent("fruits", k -> new ArrayList<>());
        values.add("apples");
        List<String> values2= multiMap.computeIfAbsent("fruits", k -> new ArrayList<>());
        values2.add("bananaa");

        Map<Character, Integer> charCount = new HashMap<>();
        String name = "Seetharamrieddy Mittala";
        for (char c : name.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            charCount.computeIfAbsent(c, k -> 0);
        }
        System.out.println("hold...");
    }
}
