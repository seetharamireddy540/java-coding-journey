package com.example.javacodingjourney.ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BinaryTreeApp {

    public static void main(String[] args) {
        Node root = buiildTree();
        Node symmetricTree = createSymmetricTree();
        BinaryTreeApp binaryTree = new BinaryTreeApp(symmetricTree);
        System.out.println("Is Symmetric Tree -" + binaryTree.isSymmetric(symmetricTree));
        binaryTree.printLevelOrder();
        //binaryTree.buildParentMap();
//        MaxDepthAndNodes maxDepthAndNodes = new MaxDepthAndNodes();
//        maxDepthAndNodes.findMaxDepthAndNodesLevelOrder(root);
//        maxDepthAndNodes.getNodesAtMaxDepth().forEach(System.out::print);
//        binaryTree.levelOrderIterative();
//        binaryTree.inorderIterative();
//        binaryTree.preorderIterative();
//        binaryTree.postOrderIterativeTwoStack();
//        binaryTree.postOrderIterativeOneStack();
//        binaryTree.inorder();
//        binaryTree.preorder();
//        binaryTree.postorder();
    }
    private static Node createSymmetricTree() {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(2);
        root.setLeft(node1);
        root.setRight(node2);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        node1.setLeft(node3);
        node1.setRight(node4);

        Node node5 = new Node(5);
        Node node6 = new Node(4);
        node2.setLeft(node5);
        node2.setRight(node6);
        return root;
    }
    private static Node buiildTree() {
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
        return root;
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

    private boolean isMirror(Node left, Node right) {
        // If both nodes are null, they are mirror images
        if (left == null && right == null) {
            return true;
        }

        // If only one node is null, they are not mirror images
        if (left == null || right == null) {
            return false;
        }

        // Check three conditions:
        // 1. Current nodes have same value
        // 2. Left's left matches Right's right
        // 3. Left's right matches Right's left
        return (left.getData() == right.getData())
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
    public boolean isSymmetric(Node node) {
        if (node == null) {
            return true;
        }
        return isMirror(node.getLeft(), node.getRight());
    }

    public void levelOrderIterative() {
        if (root == null) {
            return;
        }
        Deque<Node> queue = new ArrayDeque<>();
        HashMap<Integer, List<Node>> levelMap = new HashMap<>();

        queue.addFirst(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int sizeOfQueue = queue.size();
            levelMap.put(level, new ArrayList<>());
            System.out.println("\nLevel " + level);
            for (int i = 0; i < sizeOfQueue; i++) {
                Node node = queue.removeLast();
                System.out.print(node.getData() + " ");
                levelMap.get(level).add(node);
                if (node.getLeft() != null) {
                    queue.addFirst(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.addFirst(node.getRight());
                }
            }
            level++;
        }
        System.out.println(levelMap.get(level - 1).size());
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

    public void printLevelOrder() {
        if (this.root == null) return;
        Deque<Node> queue = new ArrayDeque<>();
        queue.addFirst(this.root);
        int level = 0;
        while(!queue.isEmpty()) {
            int sizeOfQueue = queue.size();
            System.out.println("\nLevel " + level);
            for (int i = 0; i < sizeOfQueue; i++) {
                Node node = queue.removeFirst();
                System.out.print(node.getData()+",");
                if (node.getLeft() != null) {
                    queue.addLast(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.addLast(node.getRight());
                }
            }
            level++;

        }
    }

    public void buildParentMap() {
        if (this.root == null) return;
        Map<Node, Node> parentMap = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(this.root);
        parentMap.put(this.root, null);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.getRight() != null) {
                stack.push(current.getRight());
                parentMap.put(current.getRight(), current);
            }
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
                parentMap.put(current.getLeft(), current);
            }
        }

        parentMap.forEach((child, parent) -> {
            if (parent != null && child != null) {
                System.out.println(child.getData() + " -> " + parent.getData());
            }
        });
    }
    public Node lowestCommonAncestorRecursive(Node root, Node p, Node q) {
        // Base case: if the root is null or matches either p or q, return root
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recursively search for p and q in the left and right subtrees
        Node left = lowestCommonAncestorRecursive(root.left, p, q);
        Node right = lowestCommonAncestorRecursive(root.right, p, q);

        // If both left and right are non-null, the current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // If only one subtree contains p or q, return that subtree's result
        return left != null ? left : right;
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        // Step 1: Build a parent map using iterative traversal
        Map<Node, Node> parent = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        parent.put(root, null); // Root has no parent

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            // Push the right child first (so that the left child is processed first)
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }

            // Push the left child
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
        }

        // Step 2: Build a set of ancestors for node p
        Set<Node> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p); // Move to the parent
        }

        // Step 3: Find the first common ancestor of node q in the ancestors set
        while (!ancestors.contains(q)) {
            q = parent.get(q); // Move to the parent
        }

        return q; // This is the LCA
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

    public static class MaxDepthAndNodes {
        private int maxDepth = 0; // To store the maximum depth
        private List<Integer> nodesAtMaxDepth = new ArrayList<>(); // To store nodes at max depth


        public void findMaxDepthAndNodes(Node root) {
            if (root == null) {
                return;
            }
            dfs(root, 0); // Start DFS from the root with depth 0
        }

        private void dfs(Node node, int depth) {
            if (node == null) {
                return;
            }

            // If the current depth is greater than maxDepth, update maxDepth and clear the list
            if (depth > maxDepth) {
                maxDepth = depth;
                nodesAtMaxDepth.clear();
            }

            // If the current depth equals maxDepth, add the node's value to the list
            if (depth == maxDepth) {
                nodesAtMaxDepth.add(node.data);
            }

            // Recursively traverse the left and right subtrees
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }

        public void findMaxDepthAndNodesLevelOrder(Node root) {
            if (root == null) {
                return;
            }

            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(root); // Add the root to the queue


            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                nodesAtMaxDepth.clear(); // Clear the list for the current level

                // Process all nodes at the current level
                for (int i = 0; i < levelSize; i++) {
                    Node node = queue.poll();
                    nodesAtMaxDepth.add(node.data); // Add the node to the current level list

                    // Add the left and right children to the queue
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                maxDepth++; // Increment the depth after processing a level
            }

            System.out.println("Maximum Depth: " + maxDepth);
            System.out.println("Nodes at Maximum Depth: " + nodesAtMaxDepth);
        }

        public int getMaxDepth() {
            return maxDepth;
        }

        public List<Integer> getNodesAtMaxDepth() {
            return nodesAtMaxDepth;
        }

    }
}
