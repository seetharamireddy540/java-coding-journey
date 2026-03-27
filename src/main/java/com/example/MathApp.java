package com.example;

public class MathApp {
    public static void main(String[] args) {
        MathUtils mathUtils = new MathUtils();
        int result = mathUtils.add(5, 3);
        System.out.println("5 + 3 = " + result);
    }
}
