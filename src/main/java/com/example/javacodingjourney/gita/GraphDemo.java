package com.example.javacodingjourney.gita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDemo {
    public static void main(String[] args) {
        // Create an Adjacency Matrix Graph
        int vertices = 5;
        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(vertices);
        matrixGraph.addEdge(0, 1);
        matrixGraph.addEdge(0, 4);
        matrixGraph.addEdge(1, 2);
        matrixGraph.addEdge(1, 3);
        matrixGraph.addEdge(1, 4);

        System.out.println("Original Adjacency Matrix:");
        matrixGraph.printMatrix();

        // Convert Matrix to Adjacency List
        AdjacencyListGraph listGraph = convertMatrixToList(matrixGraph, vertices);

        System.out.println("\nConverted Adjacency List:");
        listGraph.printList();

        // Convert back to Matrix
        AdjacencyMatrixGraph reconstructedMatrix = convertListToMatrix(listGraph, vertices);

        System.out.println("\nReconstructed Adjacency Matrix:");
        reconstructedMatrix.printMatrix();
    }

    // Matrix to List Conversion
    public static AdjacencyListGraph convertMatrixToList(AdjacencyMatrixGraph matrixGraph, int vertices) {
        AdjacencyListGraph listGraph = new AdjacencyListGraph();

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrixGraph.hasEdge(i, j)) {
                    listGraph.addEdge(i, j);
                }
            }
        }

        return listGraph;
    }

    // List to Matrix Conversion
    public static AdjacencyMatrixGraph convertListToMatrix(AdjacencyListGraph listGraph, int vertices) {
        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(vertices);

        for (int i = 0; i < vertices; i++) {
            List<Integer> neighbors = listGraph.getNeighbors(i);
            for (int neighbor : neighbors) {
                matrixGraph.addEdge(i, neighbor);
            }
        }

        return matrixGraph;
    }

    public static class AdjacencyMatrixGraph {
        private int[][] adjacencyMatrix;
        private int numVertices;

        // Constructor
        public AdjacencyMatrixGraph(int vertices) {
            this.numVertices = vertices;
            this.adjacencyMatrix = new int[vertices][vertices];
        }

        // Add an edge
        public void addEdge(int source, int destination) {
            // For undirected graph
            adjacencyMatrix[source][destination] = 1;
            adjacencyMatrix[destination][source] = 1;
        }

        // Remove an edge
        public void removeEdge(int source, int destination) {
            adjacencyMatrix[source][destination] = 0;
            adjacencyMatrix[destination][source] = 0;
        }

        // Check if an edge exists
        public boolean hasEdge(int source, int destination) {
            return adjacencyMatrix[source][destination] == 1;
        }

        // Print the matrix
        public void printMatrix() {
            for (int[] row : adjacencyMatrix) {
                for (int val : row) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
        }
    }

    public static class AdjacencyListGraph {
        private Map<Integer, List<Integer>> adjacencyList;

        // Constructor
        public AdjacencyListGraph() {
            adjacencyList = new HashMap<>();
        }

        // Add vertex
        public void addVertex(int vertex) {
            adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        }

        // Add edge
        public void addEdge(int source, int destination) {
            // For undirected graph
            addVertex(source);
            addVertex(destination);
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
        }

        // Get neighbors of a vertex
        public List<Integer> getNeighbors(int vertex) {
            return adjacencyList.getOrDefault(vertex, new ArrayList<>());
        }

        // Remove edge
        public void removeEdge(int source, int destination) {
            if (adjacencyList.containsKey(source)) {
                adjacencyList.get(source).remove(Integer.valueOf(destination));
            }
            if (adjacencyList.containsKey(destination)) {
                adjacencyList.get(destination).remove(Integer.valueOf(source));
            }
        }

        // Print the adjacency list
        public void printList() {
            for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
                System.out.print(entry.getKey() + ": ");
                System.out.println(entry.getValue());
            }
        }
    }

    public static class GraphConverter {
        public static AdjacencyListGraph convertToAdjacencyList(AdjacencyMatrixGraph matrixGraph, int vertices) {
            AdjacencyListGraph listGraph = new AdjacencyListGraph();

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matrixGraph.hasEdge(i, j)) {
                        listGraph.addEdge(i, j);
                    }
                }
            }

            return listGraph;
        }

        public static AdjacencyMatrixGraph convertToAdjacencyMatrix(AdjacencyListGraph listGraph, int vertices) {
            AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(vertices);

            for (int i = 0; i < vertices; i++) {
                // Assuming the list graph has a method to get adjacency list for a vertex
                // This is a hypothetical method, you'd need to implement it in AdjacencyListGraph
                List<Integer> neighbors = listGraph.getNeighbors(i);
                for (int neighbor : neighbors) {
                    matrixGraph.addEdge(i, neighbor);
                }
            }

            return matrixGraph;
        }
    }
}