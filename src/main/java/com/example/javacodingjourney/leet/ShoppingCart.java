package com.example.javacodingjourney.leet;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items = new ArrayList<>();

    public void addItem(String name, double price, int quantity) {
        Item item = new Item(name, price, quantity);

    }
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
