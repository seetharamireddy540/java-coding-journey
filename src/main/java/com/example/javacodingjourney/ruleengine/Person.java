package com.example.javacodingjourney.ruleengine;


public class Person {
    private String name;
    private int age;
    private double income;
    private String gender;

    public Person(String name, int age, double income, String gender) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.gender = gender;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getIncome() { return income; }
    public String getGender() { return gender; }
}