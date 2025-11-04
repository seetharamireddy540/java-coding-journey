package com.example.javacodingjourney.util;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PracticeUtil {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", 20),
                new Student("Bob", 22),
                new Student("Charlie", 21)
        );

    }

    @Builder
    @Getter
    public static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
