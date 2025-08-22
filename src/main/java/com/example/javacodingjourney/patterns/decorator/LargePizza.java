package com.example.javacodingjourney.patterns.decorator;

public class LargePizza extends Pizza {

    public LargePizza(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 20;
    }
}
