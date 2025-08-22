package com.example.javacodingjourney.patterns.decorator;

import lombok.Getter;

@Getter
public abstract class Pizza {

    public String description;


    public Pizza(String description) {
        this.description = description;
    }

    public abstract double getCost();
}
