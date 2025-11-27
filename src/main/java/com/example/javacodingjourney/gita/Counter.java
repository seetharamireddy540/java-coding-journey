package com.example.javacodingjourney.gita;

/**
 * Thraed
 */
public class Counter {

    private int count;

    public void increment() {


        count++;


    }

    public int getCount() {
        return count;
    }

    public void printCount() {
        System.out.println("Count: " + count);
    }
}
