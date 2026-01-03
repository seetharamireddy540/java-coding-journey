package com.example.javacodingjourney.patterns.creational.singleton;

public class SingletonApp {

    public static void main(String arg[]) {

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("Both instances are the same");
        }
        singleton1.showMessage();
    }
}

