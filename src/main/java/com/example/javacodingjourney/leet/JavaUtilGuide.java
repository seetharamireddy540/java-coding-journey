package com.example.javacodingjourney.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JavaUtilGuide {

    public static void main(String[] args) {
        demonstrateLinkedList();
    }

    public static void demonstrateArrayList() {
        System.out.println("\n=== ArrayList Demo ===");
        List<String> shoppingCart = new ArrayList<>();
        List<Integer> scores = new ArrayList<>(Arrays.asList(98, 78, 54));
        // Basic operations - O(1) for add at end, O(n) for add at index
        shoppingCart.add("Laptop");
        shoppingCart.add("Mouse");
        shoppingCart.add("Keyboard");
    }

    public static class ShoppingCart {
        private List<Item> items = new ArrayList<>();

        public void addItem(String name, double price, int quantity) {
            Item item = new Item(name, price, quantity);
            items.add(item);
        }

        static class Item {
            private final String name;
            private final double price;
            private final int quantity;

            public Item(String name, double price, int quantity) {
                this.name = name;
                this.price = price;
                this.quantity = quantity;
            }

            public String getName() {
                return name;
            }

            public double getPrice() {
                return price;
            }

            public int getQuantity() {
                return quantity;
            }
        }
    }

    public static void demonstrateLinkedList() {

        class BrowseHistory {
            private final LinkedList<String> history = new LinkedList<>();
            private int currentIndex = -1;

            public void visit(String url) {
                // Remove forward history when visiting new page
                while (history.size() > currentIndex + 1) {
                    history.removeLast();
                }
                history.add(url);
                currentIndex++;
                System.out.println("Visited: " + url);
            }

            public String back() {
                if (currentIndex > 0) {
                    currentIndex--;
                    return history.get(currentIndex);
                }
                return null;
            }

            public String forward() {
                if (currentIndex < history.size() - 1) {
                    currentIndex++;
                    return history.get(currentIndex);
                }
                return null;
            }
        }
        BrowseHistory browser = new BrowseHistory();
        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        System.out.println("Back to: " + browser.back());
        System.out.println("Back to: " + browser.back());
        System.out.println("Forward to: " + browser.forward());

        // As a Queue (FIFO)
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("Task 1");
        taskQueue.offer("Task 2");
        System.out.println("Process: " + taskQueue.poll());

        // As a Stack (LIFO)
        Deque<String> stack = new LinkedList<>();
        stack.push("Bottom");
        stack.push("Top");
        System.out.println("Pop: " + stack.pop());


    }
}
