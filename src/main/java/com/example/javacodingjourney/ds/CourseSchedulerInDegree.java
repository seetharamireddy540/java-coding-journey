package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedulerInDegree {

    public static List<String> findCourseSequenceBFS(String[] courses, Map<String, List<String>> dependencies) {
        // Create adjacency list and in-degree map
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // Initialize the graph
        for (String course : courses) {
            graph.put(course, new ArrayList<>());
            inDegree.put(course, 0);
        }

        // Build the graph and calculate in-degrees
        for (Map.Entry<String, List<String>> entry : dependencies.entrySet()) {
            String course = entry.getKey();
            for (String prerequisite : entry.getValue()) {
                graph.get(prerequisite).add(course);
                inDegree.put(course, inDegree.get(course) + 1);
            }
        }

        // Use a queue for BFS
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        // Add all courses with no prerequisites to queue
        for (String course : courses) {
            if (inDegree.get(course) == 0) {
                queue.offer(course);
            }
        }

        // Process the queue
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            // Update in-degrees of neighbors
            for (String neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if all courses are included (cycle detection)
        return result.size() == courses.length ? result : null;
    }

    // Approach 2: Using DFS
    public static List<String> findCourseSequenceDFS(String[] courses, Map<String, List<String>> dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> currentPath = new HashSet<>();
        LinkedList<String> result = new LinkedList<>();

        // Initialize graph
        for (String course : courses) {
            graph.put(course, new ArrayList<>());
        }

        // Build graph
        for (Map.Entry<String, List<String>> entry : dependencies.entrySet()) {
            String course = entry.getKey();
            for (String prerequisite : entry.getValue()) {
                graph.get(prerequisite).add(course);
            }
        }

        // DFS for each unvisited course
        for (String course : courses) {
            if (!dfs(course, graph, visited, currentPath, result)) {
                return null; // Cycle detected
            }
        }

        return result;
    }

    private static boolean dfs(String course, Map<String, List<String>> graph,
                               Set<String> visited, Set<String> currentPath,
                               LinkedList<String> result) {
        if (currentPath.contains(course)) {
            return false; // Cycle detected
        }
        if (visited.contains(course)) {
            return true;
        }

        currentPath.add(course);
        visited.add(course);

        for (String neighbor : graph.get(course)) {
            if (!dfs(neighbor, graph, visited, currentPath, result)) {
                return false;
            }
        }

        currentPath.remove(course);
        result.addFirst(course);
        return true;
    }

    public static void main(String[] args) {
        // Test case setup
        String[] courses = {"C1", "C2", "C3", "C4", "C5", "C6", "C7"};
        Map<String, List<String>> dependencies = new HashMap<>();

        dependencies.put("C1", Arrays.asList("C2", "C3"));
        dependencies.put("C2", Arrays.asList("C6", "C3"));
        dependencies.put("C3", Arrays.asList("C4", "C5", "C6"));
        dependencies.put("C4", Arrays.asList("C5", "C6"));
        dependencies.put("C5", Arrays.asList("C6", "C7"));
        dependencies.put("C6", List.of("C7"));
        dependencies.put("C7", new ArrayList<>());

        // Test BFS approach
        System.out.println("BFS Approach:");
        List<String> bfsResult = findCourseSequenceBFS(courses, dependencies);
        if (bfsResult != null) {
            System.out.println("Valid sequence found: " + bfsResult);
            System.out.println("Sequence is valid: " +
                    CourseValidator.isValidSequence(bfsResult, dependencies));
        } else {
            System.out.println("No valid sequence exists (cycle detected)");
        }

        // Test DFS approach
        System.out.println("\nDFS Approach:");
        List<String> dfsResult = findCourseSequenceDFS(courses, dependencies);
        if (dfsResult != null) {
            System.out.println("Valid sequence found: " + dfsResult);
            System.out.println("Sequence is valid: " +
                    CourseValidator.isValidSequence(dfsResult, dependencies));
        } else {
            System.out.println("No valid sequence exists (cycle detected)");
        }
    }

    // Utility class for course validation
    public static class CourseValidator {
        public static boolean isValidSequence(List<String> sequence,
                                              Map<String, List<String>> dependencies) {
            Set<String> completed = new HashSet<>();

            for (String course : sequence) {
                // Check if all prerequisites are completed
                if (dependencies.containsKey(course)) {
                    for (String prerequisite : dependencies.get(course)) {
                        if (!completed.contains(prerequisite)) {
                            return false;
                        }
                    }
                }
                completed.add(course);
            }
            return true;
        }
    }
}
