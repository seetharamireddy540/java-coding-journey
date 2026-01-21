package com.example.javacodingjourney.gita;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RamGraph {

    public static void main(String[] args) {

        Map<String, List<String>> adjacencyList = new HashMap<>();
        adjacencyList.put("Ram", List.of("Shyam", "Mohan", "Gita"));
        adjacencyList.put("Gita", List.of("Seetha", "Vedh"));
        adjacencyList.put("Vedh", List.of("Ram"));

        Queue<String> temp = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        temp.add("Ram");
        while(!temp.isEmpty()) {
            System.out.println(adjacencyList.get("Ram"));
            System.out.println("Size " + adjacencyList.get("Ram").size());
            List<String> firends = adjacencyList.get("Ram");


            for (String friend : firends){
                if (!visited.contains(friend)) {
                    visited.add(friend))
                    temp.add(friend);
                }

            }
        }


    }
}
