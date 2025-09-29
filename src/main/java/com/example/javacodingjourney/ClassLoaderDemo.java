package com.example.javacodingjourney;

public class ClassLoaderDemo {

    public static void main(String[] args) {

        ClassLoader appClassLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("Application ClassLoader: " + appClassLoader);
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println("Platform ClassLoader: " + extClassLoader);

        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("Bootstrap ClassLoader: " + bootstrapLoader);

        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println("String ClassLoader: " + stringClassLoader); // null = Bootstrap
        System.out.println("String classloader: " + String.class.getClassLoader());
        System.out.println("Integer classloader: " + Integer.class.getClassLoader());
        System.out.println("Object classloader: " + Object.class.getClassLoader());
        System.out.println("Ananya classloader: " + Ananya.class.getClassLoader());
    }
}
