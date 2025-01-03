package com.example.javacodingjourney.ds;

public class LinkedListApp {
    public static void main(String[] args) {

    }

    public static class  LinkedList {
        private Node first;

        public LinkedList() {
            first = null;
        }

        public void insertFirst(int data) {
            Node newNode = new Node(data);
            newNode.setNext(first);
            first = newNode;
        }

        public Node deleteFirst() {
            Node temp = first;
            first = first.getNext();
            return temp;
        }

        public void displayList() {
            System.out.println("List (first --> last)");
            Node current = first;
            while (current != null) {
                current.displayNode();
                current = current.getNext();
            }
            System.out.println();
        }
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
        public void displayNode() {
            System.out.println("{" + data + "}");
        }
        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public int getData() {
            return data;
        }
    }
}
