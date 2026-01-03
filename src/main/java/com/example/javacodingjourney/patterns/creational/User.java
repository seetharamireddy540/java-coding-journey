package com.example.javacodingjourney.patterns.creational;

public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.email = builder.email;
    }

    // Static nested class for Builder pattern
    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private String email;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return String.format("User[name=%s %s, age=%d, email=%s]",
                firstName, lastName, age, email);
    }

    public static void main(String[] args) {
        // Using static nested builder
        User user = new User.UserBuilder("Ram", "Mittala")
                .age(42)
                .email("ram@example.com")
                .build();

        System.out.println(user);
    }
}