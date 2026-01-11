package com.example.javacodingjourney.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonAgeSortingApp {

    public static void main(String[] args) {
        Person[] people = new Person[]{new Person(30, "zhangsan"),
                new Person(10, "lisi"),
                new Person(20, "wangwu")};

        List<Person> peopleList = Arrays.asList(people);
        Collections.sort(peopleList, (p1, p2) -> {
            if (p1.age < p2.age) return -1;
            if (p1.age > p2.age) return 1;
            return 0;
        });
        System.out.println("Youngest first: " + peopleList);

    }

    public static class Person {
        String name;
        int age;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
