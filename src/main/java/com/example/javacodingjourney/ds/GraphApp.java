package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

public class GraphApp {

    public static void main(String[] args) {
        GraphApp graph = new GraphApp(6);
        graph.addEdge(0, 1);
    }

    private final int vertices;
    private final List<List<Integer>> adjList;

    public GraphApp(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }
    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
    }
}
