package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

public class GraphApp {

    private final int vertices;
    private final List<List<Integer>> adjList;
    public GraphApp(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        GraphApp graph = new GraphApp(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.printGraph();
    }

    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + "-> {");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + ",");
            }
            System.out.println("}");
        }
    }
}
