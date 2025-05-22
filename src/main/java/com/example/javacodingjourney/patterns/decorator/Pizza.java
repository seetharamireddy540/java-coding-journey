package com.example.javacodingjourney.patterns.decorator;

public abstract class Pizza {

    public String description;


    public Pizza(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
    public abstract double getCost();
}
