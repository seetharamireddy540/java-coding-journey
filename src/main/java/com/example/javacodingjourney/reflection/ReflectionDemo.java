package com.example.javacodingjourney.reflection;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {

        inspectClass(String.class);

        // Ways to get Class object
//        Class<?> stringClass1 = String.class;  // Using .class
//        Class<?> stringClass2 = "Hello".getClass();  // Using getClass()
//        try {
//            Class<?> stringClass3 = Class.forName("java.lang.String");  // Using forName()
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void inspectClass(Class<?> clazz) {
        // Basic class information
        System.out.println("Class Name: " + clazz.getName());
        System.out.println("Simple Name: " + clazz.getSimpleName());
        System.out.println("Package: " + clazz.getPackage());

        // Superclass
        System.out.println("Superclass: " + clazz.getSuperclass());

        // Implemented Interfaces
        System.out.println("Interfaces:");
        for (Class<?> interfaces : clazz.getInterfaces()) {
            System.out.println(" - " + interfaces.getName());
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("Number of Methods: " + methods.length);
    }

    public void demonstrateMethodReflection() throws Exception {
        // Get all methods
        Class<?> stringClass = String.class;
        Method[] methods = stringClass.getDeclaredMethods();

        // Find specific method
        Method lengthMethod = stringClass.getMethod("length");

        // Invoke method dynamically
        String sample = "Hello";
        int length = (int) lengthMethod.invoke(sample);
        System.out.println("Length: " + length);

        // Invoke private method
//        Method privateMethod = SomeClass.class.getDeclaredMethod("privateMethod");
//        privateMethod.setAccessible(true);  // Override access restrictions
//        privateMethod.invoke(new SomeClass());
    }
}