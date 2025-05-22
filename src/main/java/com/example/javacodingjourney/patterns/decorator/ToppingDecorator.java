package com.example.javacodingjourney.patterns.decorator;

public abstract class ToppingDecorator extends Pizza {

    public ToppingDecorator(Pizza pizza) {
        super(pizza.getDescription());
    }

    public abstract String getDescription();
}
