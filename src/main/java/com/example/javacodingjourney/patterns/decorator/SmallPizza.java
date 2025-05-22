package com.example.javacodingjourney.patterns.decorator;

public class SmallPizza extends Pizza {

    public SmallPizza(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 10;
    }
}
