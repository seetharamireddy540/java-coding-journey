package com.example.javacodingjourney.ds.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(Integer vertex) {
        this.adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Integer source, Integer destination) {
        this.addVertex(source);
        this.addVertex(destination);
        this.adjacencyList.get(source).add(destination);
        this.adjacencyList.get(destination).add(source);
    }

    public Set<Integer> getAllVertex() {
        return this.adjacencyList.keySet();
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }

    public void dfs() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> vertices = this.getAllVertex();
        System.out.println("DFS Traversal");
        for (Integer vertex : vertices) {
            if (!visited.contains(vertex)) {
                boolean hasCycle = dfsUtil(vertex, visited);
            }
        }
    }

    public boolean dfsUtil(Integer currentVertex, Set<Integer> visited) {
        visited.add(currentVertex);
        System.out.println(" " + currentVertex);
        List<Integer> neighbours = this.getNeighbours(currentVertex);
        for (Integer neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                return dfsUtil(neighbour, visited);
            }
        }
        return false;
    }

    public void bfs() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> vertices = this.getAllVertex();

        for (Integer vertex : vertices) {
            if (!visited.contains(vertex)) {
                boolean hasCycle = bfsUtil(vertex, visited);
            }
        }
    }

    public boolean bfsUtil(Integer currentVertex, Set<Integer> visited) {
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        visited.add(currentVertex);
        queue.offer(currentVertex);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            System.out.print(current + " ");
            List<Integer> neighbours = this.getNeighbours(current);
            for (Integer neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
        int allVertices = this.getAllVertex().size();
        int visitedVertices = visited.size();
        // has cycle
        return visitedVertices == allVertices; // no cycles and visited all
    }

    public List<Integer> getNeighbours(Integer vertex) {
        return this.adjacencyList.get(vertex);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.printGraph();

        graph.bfs();
        graph.dfs();

    }
}
