package com.example.javacodingjourney.ds;

import java.util.ArrayDeque;
import java.util.Deque;

public class KthSmallestElement {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(16);
        inordre(root);
        System.out.println("");
        System.out.println("K smallest element is -> " + kthSmallest(root, 1));

    }

    public static void inordre(TreeNode root) {

        if (root == null) {
            return;
        }
        inordre(root.left);
        System.out.print(root.val + " ");
        inordre(root.right);
    }

    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }
}
