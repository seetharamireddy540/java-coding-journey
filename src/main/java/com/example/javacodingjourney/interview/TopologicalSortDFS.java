package com.example.javacodingjourney.interview;

import java.util.*;

public class TopologicalSortDFS {

    // DFS-based Topological Sort
    public static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        // Call DFS for all unvisited vertices
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        // Pop all vertices from stack
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static void dfs(int node, List<List<Integer>> adj,
                            boolean[] visited, Deque<Integer> stack) {
        visited[node] = true;

        // Visit all neighbors
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stack);
            }
        }

        // Push current node to stack after visiting all neighbors
        stack.push(node);
    }

    // DFS with Cycle Detection
    public static List<Integer> topologicalSortWithCycleCheck(int V, List<List<Integer>> adj) {
        int[] state = new int[V]; // 0: unvisited, 1: visiting, 2: visited
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state, stack)) {
                    return new ArrayList<>(); // Cycle detected
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static boolean hasCycle(int node, List<List<Integer>> adj,
                                    int[] state, Deque<Integer> stack) {
        state[node] = 1; // Mark as visiting

        for (int neighbor : adj.get(node)) {
            if (state[neighbor] == 1) {
                return true; // Back edge found (cycle)
            }
            if (state[neighbor] == 0 && hasCycle(neighbor, adj, state, stack)) {
                return true;
            }
        }

        state[node] = 2; // Mark as visited
        stack.push(node);
        return false;
    }
}