package com.example.javacodingjourney.poly;

public class Factorial {
    public static int factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }
        // Recursive case
        else {
            int temp = factorial(n-1);
            return n * temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("Factorial of 5 is: " + factorial(5)); // 120
    }
}