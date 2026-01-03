package com.example.javacodingjourney.patterns.creational.singleton;

public class Singleton {

    private static Singleton instance;

    private Singleton() {
        // Private constructor to prevent instantiation from outside the class
        System.out.println("Singleton instance created");
    }
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
