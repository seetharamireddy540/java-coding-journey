package com.example.javacodingjourney.patterns.structural;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The Composite Design Pattern is a structural pattern that allows you to compose objects into tree-like structures to represent part-whole hierarchies.
 * It lets clients treat individual objects and compositions of objects uniformly.
 */
public class CompositeDemo {
    public static void main(String[] args) {
        // Create directories
        Directory rootDir = new Directory("Root");
        Directory documentDir = new Directory("Documents");
        Directory picturesDir = new Directory("Pictures");

        // Create files
        File doc1 = new File("report.docx", 1024);
        File doc2 = new File("presentation.pptx", 2048);
        File pic1 = new File("vacation.jpg", 3072);
        File pic2 = new File("landscape.png", 4096);

        // Compose structure
        documentDir.addComponent(doc1);
        documentDir.addComponent(doc2);

        picturesDir.addComponent(pic1);
        picturesDir.addComponent(pic2);

        rootDir.addComponent(documentDir);
        rootDir.addComponent(picturesDir);

        // Display and calculate total size
        rootDir.display();
        System.out.println("Total Size: " + rootDir.getSize() + " bytes");
    }

    public static interface FileSystemComponent {
        void display();

        long getSize();
    }


    // Leaf Class (Individual File)
    public static class File implements FileSystemComponent {
        private String name;
        private long size;

        public File(String name, long size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public void display() {
            System.out.println("File: " + name + ", Size: " + size + " bytes");
        }

        @Override
        public long getSize() {
            return size;
        }
    }

    // Composite Class (Directory)
    public static class Directory implements FileSystemComponent {
        private String name;
        private List<FileSystemComponent> components;

        public Directory(String name) {
            this.name = name;
            this.components = new ArrayList<>();
        }

        public void addComponent(FileSystemComponent component) {
            components.add(component);
        }

        public void removeComponent(FileSystemComponent component) {
            components.remove(component);
        }

        @Override
        public void display() {
            System.out.println("Directory: " + name);
            for (FileSystemComponent component : components) {
                component.display();
            }
        }

        @Override
        public long getSize() {
            return components.stream()
                    .mapToLong(FileSystemComponent::getSize)
                    .sum();
        }
    }
}
