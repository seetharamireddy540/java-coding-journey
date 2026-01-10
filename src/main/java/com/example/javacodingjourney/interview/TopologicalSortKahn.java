package com.example.javacodingjourney.interview;

import java.util.*;

public class TopologicalSortKahn {

    // Kahn's Algorithm using BFS
    public static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        // Calculate in-degree for each vertex
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Queue for vertices with 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            // Reduce in-degree for neighbors
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if topological sort is possible (cycle detection)
        if (result.size() != V) {
            return new ArrayList<>(); // Cycle exists
        }

        return result;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Example: 5 → 2 → 3 → 1
        //             ↓     ↓
        //             0     4
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> result = topologicalSort(V, adj);
        System.out.println("Topological Sort: " + result);
        // Output: [4, 5, 0, 2, 3, 1] or [5, 4, 2, 3, 0, 1]
    }
}