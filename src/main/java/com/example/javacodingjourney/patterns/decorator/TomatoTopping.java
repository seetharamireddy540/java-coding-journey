package com.example.javacodingjourney.patterns.decorator;

public class TomatoTopping extends ToppingDecorator {
    private Pizza pizza;

    public TomatoTopping(Pizza pizza) {
        super(pizza);
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Tomato";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 2;
    }
}
