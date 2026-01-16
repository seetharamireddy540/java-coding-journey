package com.example.javacodingjourney.ds.tree;

public class TreeNode {
    private int val;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(int val) {
        this.val = val;
        this.leftNode = null;
        this.rightNode = null;

    }

    public  void inOrder(TreeNode root){
        if(root==null){
            return;
        }
        inOrder(root.leftNode);
        System.out.print(root.val+" ");
        inOrder(root.rightNode);
    }

     public void nonRecursiveInOrder(TreeNode root){
         if(root==null){
             return;
         }
         java.util.Stack<TreeNode> stack = new java.util.Stack<>();
         TreeNode current = root;

     }
     public void postOrderNonRecursive(TreeNode root){

         if(root==null){
             return;
         }

     }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.leftNode = new TreeNode(5);
        root.rightNode = new TreeNode(15);
        root.leftNode.leftNode = new TreeNode(2);
        root.rightNode.leftNode = new TreeNode(12);
        root.inOrder(root);

    }

}
