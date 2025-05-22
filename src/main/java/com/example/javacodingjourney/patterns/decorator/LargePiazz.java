package com.example.javacodingjourney.patterns.decorator;

public class LargePiazz extends Pizza {

    public LargePiazz(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 20;
    }
}
