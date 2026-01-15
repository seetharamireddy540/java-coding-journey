package com.example.javacodingjourney.reflection;

public class MyTest {

    @RamTest(value = "This is a test", enabled = false)
    public void testMethod1() {
        System.out.println("Running testMethod1");
    }
}
