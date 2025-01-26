package com.example.javacodingjourney.ds;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTreeApp {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        root.setLeft(node1);
        root.setRight(node2);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        node1.setLeft(node3);
        node1.setRight(node4);

        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.setLeft(node5);
        node2.setRight(node6);
        BinaryTreeApp binaryTree = new BinaryTreeApp(root);

        binaryTree.inorderIterative();
        binaryTree.preorderIterative();
        binaryTree.postOrderIterativeTwoStack();
        binaryTree.postOrderIterativeOneStack();
        binaryTree.inorder();
        binaryTree.preorder();
        binaryTree.postorder();
    }

    private final Node root;

    public BinaryTreeApp(Node root) {
        this.root = root;
    }

    public BinaryTreeApp(int data) {
        this.root = new Node(data);
    }

    public Node getRoot() {
        return root;
    }

    public void inorderIterative() {
        if (this.root == null) return;
        System.out.println("--------InorderIterative------");
        Deque<Node> stack = new ArrayDeque<>();
        Node current = this.root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            System.out.print(current.getData() + ",");
            current = current.getRight();
        }
        System.out.println("\n--------InorderIterative------");

    }


    public void preorderIterative() {
        if (this.root == null) return;
        System.out.println("--------preorderIterative------");

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(this.root);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.getData() + ",");
            Node left = current.getLeft();
            Node right = current.getRight();
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
        }
        System.out.println("\n--------preorderIterative------");
    }

    public void postOrderIterativeOneStack() {
        if (this.root == null) return;
        Deque<Node> stack = new ArrayDeque<>();
        Node current = this.root;
        Node lastVisited = null;
        System.out.println("\n--------postOrderIterativeOneStack------");

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                Node peekNode = stack.peek();
                if (peekNode.getRight() != null && peekNode.getRight() != lastVisited) {
                    current = peekNode.getRight();
                } else {
                    lastVisited = stack.pop();
                    System.out.print(lastVisited.getData() + ",");
                }

            }
        }
    }

    public void postOrderIterativeTwoStack() {
        if (this.root == null) return;
        System.out.println("\n--------postorderIterative------");

        Deque<Node> stack1 = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();
        stack1.push(this.root);
        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);
            if (current.getLeft() != null) {
                stack1.push(current.getLeft());
            }
            if (current.getRight() != null) {
                stack1.push(current.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            Node current = stack2.pop();
            System.out.print(current.getData() + ",");
        }
        System.out.println("\n--------postorderIterative------");

    }

    public void inorder() {
        if (this.root == null) {
            return;
        }
        System.out.println("---------------Inorder--------------");

        this.inorderHelper(root);
        System.out.println("");
    }

    public void preorder() {
        if (this.root == null) {
            return;
        }
        System.out.println("---------------Preorder--------------");

        this.preorderHelper(root);
        System.out.println("");

    }

    public void postorder() {
        if (this.root == null) {
            return;
        }
        System.out.println("---------------Postorder--------------");
        this.postorderHelper(root);
        System.out.println("");

    }

    private void inorderHelper(Node root) {
        if (root == null) {
            return;
        }
        inorderHelper(root.getLeft());
        System.out.print(root.getData() + ",");
        inorderHelper(root.getRight());
    }

    private void preorderHelper(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getData() + ",");
        preorderHelper(root.getLeft());
        preorderHelper(root.getRight());
    }

    private void postorderHelper(Node root) {
        if (root == null) {
            return;
        }
        postorderHelper(root.getLeft());
        postorderHelper(root.getRight());
        System.out.print(root.getData() + ",");
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
