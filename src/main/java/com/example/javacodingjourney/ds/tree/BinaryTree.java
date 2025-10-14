package com.example.javacodingjourney.ds.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }
    
    public void inOrderTraversal() {
        if (this.root != null){
            inOrderTraversalUtil(this.root);
            System.out.println("Now printe using iteraiton");
            inOrderTraversalUtilIteration(this.root);
        }
    }
    public void postOrderTraversal() {
        if (this.root != null){
            postOrderTraversalUtil(this.root);
        }
    }
    private void postOrderTraversalUtil(Node root) {
        if (root == null){
            return;
        }
        postOrderTraversalUtil(root.getLeft());
        postOrderTraversalUtil(root.getRight());
        System.out.println(root.getValue());
    }


    public void levelOrderTraversal() {
        if (this.root != null){
            levelOrderTraversalUtil(this.root);
        }
    }

    private void levelOrderTraversalUtil(Node root) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int level = -1;
        while (!queue.isEmpty()){
            int qSize = queue.size();
            level++;
            int levelSum = 0;
            for (int i =0 ; i<qSize ; i++){
                Node current = queue.remove();
                levelSum= levelSum + current.getValue();
                System.out.print(current.getValue() + "  ");
                if (current.getLeft() != null){
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null){
                    queue.add(current.getRight());
                }
            }
            System.out.println("\n Level = " +level + " Sum = " +levelSum);
            System.out.println("-----------------------------------");
        }
    }


    private void inOrderTraversalUtil(Node root) {
        if (root == null){
            return;
        }
        inOrderTraversalUtil(root.getLeft());
        System.out.println(root.getValue());
        inOrderTraversalUtil(root.getRight());
    }

    private void inOrderTraversalUtilIteration(Node root) {

        Node current = root;
        Deque<Node> stack = new ArrayDeque<>();
        while (current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            System.out.println(current.getValue());
            current = current.getRight();
        }

    }

    public void preOrderTraversal(){
        if (this.root != null){
            preOrderTraversalUtil(this.root);
            System.out.println("NoW i AM GOING PRINT ITERATION");
            preOrderTraversalUtilIteration(this.root);
        }
    }

    private void preOrderTraversalUtil(Node root) {
        if (root == null){
            return;
        }
        System.out.println(root.getValue());
        preOrderTraversalUtil(root.getLeft());
        preOrderTraversalUtil(root.getRight());
    }

    private void preOrderTraversalUtilIteration(Node root){

        Node current = root;
        Deque<Node> stack = new ArrayDeque<>();
        while (current != null || !stack.isEmpty()){
            while (current != null){
                System.out.println(current.getValue());
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            current = current.getRight();
        }
    }
}
