package com.example.javacodingjourney.poly.treetraversal;

import java.util.Stack;

public class TreeTraversalDemo {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    // Recursive traversal methods

    public static void inorderRecursive(TreeNode root) {
        if (root == null) return;
        inorderRecursive(root.left);
        System.out.print(root.value + " ");
        inorderRecursive(root.right);
    }

    public static void preorderRecursive(TreeNode root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        preorderRecursive(root.left);
        preorderRecursive(root.right);
    }

    public static void postorderRecursive(TreeNode root) {
        if (root == null) return;
        postorderRecursive(root.left);
        postorderRecursive(root.right);
        System.out.print(root.value + " ");
    }

    // Iterative traversal methods

    public static void inorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.value + " ");
            current = current.right;
        }
    }

    public static void preorderIterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.value + " ");

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public static void postorderIterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        // Create a sample binary tree
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   6
        //       / \
        //      7   8

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        System.out.println("Inorder Traversal (Left -> Root -> Right):");
        System.out.print("Recursive: ");
        inorderRecursive(root);
        System.out.print("\nIterative: ");
        inorderIterative(root);

        System.out.println("\n\nPreorder Traversal (Root -> Left -> Right):");
        System.out.print("Recursive: ");
        preorderRecursive(root);
        System.out.print("\nIterative: ");
        preorderIterative(root);

        System.out.println("\n\nPostorder Traversal (Left -> Right -> Root):");
        System.out.print("Recursive: ");
        postorderRecursive(root);
        System.out.print("\nIterative: ");
        postorderIterative(root);
    }
}