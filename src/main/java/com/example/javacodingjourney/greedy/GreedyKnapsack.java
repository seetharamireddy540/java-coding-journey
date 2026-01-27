package com.example.javacodingjourney.greedy;

import java.util.Arrays;

class GreedyKnapsack {
    public double getMaxValue(Item[] items, int capacity) {
        // Sort by value/weight ratio in descending order
        Arrays.sort(items, (a, b) ->
                Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Take fraction of the remaining item
                totalValue += item.value * ((double) capacity / item.weight);
                break;
            }
        }
        return totalValue;
    }

    public static class Item {
        int value, weight;

        Item(int v, int w) {
            value = v;
            weight = w;
        }
    }

}