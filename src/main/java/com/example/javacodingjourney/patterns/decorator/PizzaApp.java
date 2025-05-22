package com.example.javacodingjourney.patterns.decorator;

public class PizzaApp {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(() -> {
            try {
                System.out.println("Hello from a thread!");
                Thread.sleep(1000L);
                System.out.println("Hello from a thread!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from a thread!");
        });
        thread.start();

        Pizza pizza = new SmallPizza("Small Pizza");
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: $" + pizza.getCost());

        pizza = new TomatoTopping(pizza);

        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: $" + pizza.getCost());

        Thread.currentThread().join();

    }
}
