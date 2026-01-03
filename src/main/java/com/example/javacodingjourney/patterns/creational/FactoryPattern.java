package com.example.javacodingjourney.patterns.creational;


public class FactoryPattern {

    public static void main(String[] args) {
        Animal animal = AnimalFactory.getAnimal("dog");
        if (animal != null) {
            animal.speak();
        }
    }

    public static class AnimalFactory {
        public static Animal getAnimal(String type) {
            if ("dog".equalsIgnoreCase(type)) {
                return new Dog();
            } else if ("cat".equalsIgnoreCase(type)) {
                return new Cat();
            }
            return null;
        }
    }

    public static class Dog implements Animal {
        @Override
        public void speak() {
            System.out.println("Woof");
        }
    }

    public static class Cat implements Animal {
        @Override
        public void speak() {
            System.out.println("Meow");
        }
    }

    public static interface Animal {
        void speak();
    }
}
