package com.example.javacodingjourney;

public class Dog {

    private String name;
    private String breed;

    public Dog(String breed, String name) {
        this.breed = breed;
        this.name = name;
    }

    public void saySomething() {
        System.out.println("My nbame is " + this.name);
    }
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
