package com.example.javacodingjourney.poly;

public class StringReversal {
    public static String reverse(String str) {
        // Base case
        if (str.isEmpty() || str.length() == 1) {
            return str;
        }
        // Recursive case
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        String original = "hello";
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reverse(original)); // olleh
    }
}