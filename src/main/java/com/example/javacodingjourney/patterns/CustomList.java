package com.example.javacodingjourney.patterns;

import java.util.Iterator;

public class CustomList<T> implements Iterable<T> {
    private Object[] elements;
    private int size = 0;

    public CustomList(int capacity) {
        elements = new Object[capacity];
    }

    public void add(T element) {
        if (size < elements.length) {
            elements[size++] = element;
        }
    }

    // Inner class has access to outer class's private members
    private class ListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size; // Accessing outer class field
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T) elements[currentIndex++]; // Accessing outer class array
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        CustomList<String> list = new CustomList<>(5);
        list.add("Hello");
        list.add("World");

        // Using the inner class iterator
        for (String item : list) {
            System.out.println(item);
        }
    }
}