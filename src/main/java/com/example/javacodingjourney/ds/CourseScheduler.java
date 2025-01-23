package com.example.javacodingjourney.ds;

import java.util.*;
/**
 * Imagine Amazon University offers a certification program consisting of seven courses: C1, C2, C3, C4, C5, C6, and C7.
 * To earn the certification, students must complete all courses in a specific order based on their dependencies. The dependencies are as follows:
 *
 * - C1 depends on C2
 * - C2 depends on C6, C3
 * - C3 depends on C4
 * - C4 depends on C5
 * - C5 depends on C6, C7
 * - C6 depends on C7
 * - C7 has no dependencies
 *
 * Given this information, determine the correct sequence in which a student should take these courses to successfully complete the certification program.
 * Your task is to provide an algorithm or approach that finds the optimal order of courses, ensuring all prerequisites are met before a course is taken.
 *
 * The optimal course order is: [C7, C6, C5, C4, C3, C2, C1]
 */
public class CourseScheduler {

    public static List<String> findCourseOrder(Map<String, List<String>> graph) {
        // Create a list to store the course order
        List<String> courseOrder = new ArrayList<>();
        // Create a set to track visited courses
        Set<String> visited = new HashSet<>();
        // Create a set to detect cycles (courses currently being visited)
        Set<String> visiting = new HashSet<>();

        // Perform DFS for each course
        for (String course : graph.keySet()) {
            if (!visited.contains(course)) {
                if (!dfs(course, graph, visited, visiting, courseOrder)) {
                    throw new IllegalStateException("Cycle detected in the course dependencies!");
                }
            }
        }

        // Reverse the order to get the correct sequence
//        Collections.reverse(courseOrder);
        return courseOrder;
    }

    private static boolean dfs(String course, Map<String, List<String>> graph,
                               Set<String> visited, Set<String> visiting, List<String> courseOrder) {
        // If the course is currently being visited, a cycle is detected
        if (visiting.contains(course)) {
            return false;
        }
        // If the course has already been visited, skip it
        if (visited.contains(course)) {
            return true;
        }

        // Mark the course as being visited
        visiting.add(course);

        // Visit all prerequisites of the current course
        for (String prerequisite : graph.get(course)) {
            if (!dfs(prerequisite, graph, visited, visiting, courseOrder)) {
                return false;
            }
        }

        // Mark the course as fully visited
        visiting.remove(course);
        visited.add(course);
        // Add the course to the order
        courseOrder.add(course);

        return true;
    }

    public static void main(String[] args) {
        // Define the course dependencies
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("C1", Arrays.asList("C2"));
        graph.put("C2", Arrays.asList("C6", "C3"));
        graph.put("C3", Arrays.asList("C4"));
        graph.put("C4", Arrays.asList("C5"));
        graph.put("C5", Arrays.asList("C6", "C7"));
        graph.put("C6", Arrays.asList("C7"));
        graph.put("C7", new ArrayList<>());

        // Find the correct course order
        List<String> courseOrder = findCourseOrder(graph);

        // Print the result
        System.out.println("The optimal course order is: " + courseOrder);
    }
}