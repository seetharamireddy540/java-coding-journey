package com.example.javacodingjourney;

import java.io.File;

public class FileLocationBasics {

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        System.out.printf("Current working dir = %s%n", currentDir);
        File file = new File("data.txt");
        System.out.println(file.exists());
        String userHome = System.getProperty("user.home");

        System.out.printf("User Home - %s%n", userHome);
        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.printf("Java temp dir %s", tempDir);
    }
}
