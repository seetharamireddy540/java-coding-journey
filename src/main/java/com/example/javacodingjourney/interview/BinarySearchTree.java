package com.example.javacodingjourney.interview;

public class BinarySearchTree {
    private TreeNode root;

    // Insert a value
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertRec(root.left, val);
        } else if (val > root.val) {
            root.right = insertRec(root.right, val);
        }

        return root;
    }

    // Search for a value
    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(TreeNode root, int val) {
        if (root == null) {
            return false;
        }

        if (root.val == val) {
            return true;
        }

        if (val < root.val) {
            return searchRec(root.left, val);
        }

        return searchRec(root.right, val);
    }

    // Delete a node
    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private TreeNode deleteRec(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            root.left = deleteRec(root.left, val);
        } else if (val > root.val) {
            root.right = deleteRec(root.right, val);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get inorder successor
            root.val = minValue(root.right);
            root.right = deleteRec(root.right, root.val);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
}