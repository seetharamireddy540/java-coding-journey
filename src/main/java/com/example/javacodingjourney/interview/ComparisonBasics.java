package com.example.javacodingjourney.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparisonBasics {
    public static void main(String[] args) {
        // Basic integer comparison
        int a = 5, b = 10;

        System.out.println("Comparing a=5 with b=10:");
        System.out.println("a - b = " + (a - b));  // -5 (negative: a < b)

        int c = 10, d = 10;
        System.out.println("\nComparing c=10 with d=10:");
        System.out.println("c - d = " + (c - d));  // 0 (zero: c == d)

        int e = 15, f = 10;
        System.out.println("\nComparing e=15 with f=10:");
        System.out.println("e - f = " + (e - f));  // 5 (positive: e > f)

        // ASCENDING ORDER (Natural Order)
        // Uses (a - b) or a.compareTo(b)
        List<Integer> ascending = Arrays.asList(5, 2, 8, 1, 9);
        Collections.sort(ascending, (a2, b2) -> a2 - b2);
        System.out.println("Ascending: " + ascending);  // [1, 2, 5, 8, 9]

        // DESCENDING ORDER (Reverse Order)
        // Uses (b - a) or b.compareTo(a)
        List<Integer> descending = Arrays.asList(5, 2, 8, 1, 9);
        Collections.sort(descending, (a1, b1) -> b1 - a1);
        System.out.println("Descending: " + descending);  // [9, 8, 5, 2, 1]
    }
}