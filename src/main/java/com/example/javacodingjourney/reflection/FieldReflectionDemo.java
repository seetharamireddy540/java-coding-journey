package com.example.javacodingjourney.reflection;

import java.lang.reflect.Field;

public class FieldReflectionDemo {
    public void demonstrateFieldAccess() throws Exception {
        // Create instance
        Person person = new Person("John", 30);

        // Access private field
        Field nameField = Person.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Jane");  // Modify private field

        // Get field value
        Object value = nameField.get(person);
        System.out.println("Modified Name: " + value);
    }

    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}