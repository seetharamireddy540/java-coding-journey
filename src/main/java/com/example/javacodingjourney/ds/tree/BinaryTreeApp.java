package com.example.javacodingjourney.ds.tree;

public class BinaryTreeApp {

    public static void main(String[] args) {

        Node root = new Node(1);
        Node left = new Node(2);
        Node ll = new Node(4);
        Node lr = new Node(5);
        left.setLeft(ll);
        left.setRight(lr);
        Node right = new Node(3);
        right.setLeft(new Node(6));
        right.setRight(new Node(7));
        root.setLeft(left);
        root.setRight(right);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.levelOrderTraversal();
    }
}
