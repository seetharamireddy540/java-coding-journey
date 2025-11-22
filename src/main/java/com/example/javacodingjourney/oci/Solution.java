package com.example.javacodingjourney.oci;

import java.util.*;

class Solution {
    /**
     * Course Schedule II - Return the order of courses to finish all courses
     *
     * Example 1:
     * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * Output: [0,1,2,3] or [0,2,1,3]
     * Explanation: There are multiple valid orders
     *
     * Example 2:
     * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
     * Output: []
     * Explanation: Impossible due to cycle
     *
     * Example 3:
     * Input: numCourses = 3, prerequisites = [[1,0],[2,1]]
     * Output: [0,1,2]
     * Explanation: Must take courses in this order
     */

    // Method 1: BFS (Kahn's Algorithm) - Returns course order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build graph and indegree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build adjacency list
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // Find all courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Process courses and build order
        int[] courseOrder = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            courseOrder[index++] = course;

            // Process dependent courses
            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // Check if all courses were processed
        if (index == numCourses) {
            return courseOrder;
        } else {
            return new int[0]; // Return empty array if cycle exists
        }
    }

    // Method 2: DFS with course order tracking
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }

        // States: 0 = unvisited, 1 = visiting, 2 = visited
        int[] state = new int[numCourses];
        Stack<Integer> stack = new Stack<>(); // For reverse topological order

        // Try to visit all courses
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycleDFS(graph, state, i, stack)) {
                    return new int[0]; // Cycle detected
                }
            }
        }

        // Build result from stack (reverse order)
        int[] result = new int[numCourses];
        int index = 0;
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    private boolean hasCycleDFS(List<List<Integer>> graph, int[] state,
                                int course, Stack<Integer> stack) {
        state[course] = 1; // Mark as visiting

        for (int nextCourse : graph.get(course)) {
            if (state[nextCourse] == 1) {
                return true; // Cycle detected
            }
            if (state[nextCourse] == 0) {
                if (hasCycleDFS(graph, state, nextCourse, stack)) {
                    return true;
                }
            }
        }

        state[course] = 2; // Mark as visited
        stack.push(course); // Add to stack after visiting all dependencies
        return false;
    }

    // Method 3: Find ALL possible valid orders (for smaller inputs)
    public List<List<Integer>> findAllPossibleOrders(int numCourses, int[][] prerequisites) {
        List<List<Integer>> allOrders = new ArrayList<>();

        // Build graph and indegree
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            indegree[prereq[0]]++;
        }

        // Use backtracking to find all orders
        List<Integer> currentOrder = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        int[] currentIndegree = indegree.clone();

        findAllOrdersHelper(graph, currentIndegree, visited, currentOrder,
                allOrders, numCourses);

        return allOrders;
    }

    private void findAllOrdersHelper(List<List<Integer>> graph, int[] indegree,
                                     boolean[] visited, List<Integer> currentOrder,
                                     List<List<Integer>> allOrders, int numCourses) {
        // Base case: found complete order
        if (currentOrder.size() == numCourses) {
            allOrders.add(new ArrayList<>(currentOrder));
            return;
        }

        // Try each course with indegree 0 that hasn't been visited
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0 && !visited[course]) {
                // Choose course
                visited[course] = true;
                currentOrder.add(course);

                // Update indegrees of dependent courses
                for (int next : graph.get(course)) {
                    indegree[next]--;
                }

                // Recurse
                findAllOrdersHelper(graph, indegree, visited, currentOrder,
                        allOrders, numCourses);

                // Backtrack
                for (int next : graph.get(course)) {
                    indegree[next]++;
                }
                currentOrder.remove(currentOrder.size() - 1);
                visited[course] = false;
            }
        }
    }

    // Enhanced version with detailed output
    public void analyzeCourseDependencies(int numCourses, int[][] prerequisites) {
        System.out.println("\n=== Course Dependency Analysis ===");
        System.out.println("Number of courses: " + numCourses);
        System.out.println("Prerequisites: " + Arrays.deepToString(prerequisites));

        // Check if possible
        int[] order = findOrder(numCourses, prerequisites);

        if (order.length == 0) {
            System.out.println("❌ Cannot complete all courses - Cycle detected!");

            // Find and display the cycle
            findAndDisplayCycle(numCourses, prerequisites);
        } else {
            System.out.println("✅ Can complete all courses!");
            System.out.println("One valid order: " + Arrays.toString(order));

            // Find all possible orders (for small inputs)
            if (numCourses <= 6) {
                List<List<Integer>> allOrders = findAllPossibleOrders(numCourses, prerequisites);
                System.out.println("Total number of valid orders: " + allOrders.size());
                if (allOrders.size() <= 10) {
                    System.out.println("All possible orders:");
                    for (int i = 0; i < allOrders.size(); i++) {
                        System.out.println("  Order " + (i + 1) + ": " + allOrders.get(i));
                    }
                }
            }

            // Display course levels (which courses can be taken in parallel)
            displayCourseLevels(numCourses, prerequisites);
        }
    }

    private void displayCourseLevels(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            indegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        System.out.println("\nCourses by level (can be taken in parallel):");
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                currentLevel.add(course);

                for (int next : graph.get(course)) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            System.out.println("  Level " + level++ + ": " + currentLevel);
        }
    }

    private void findAndDisplayCycle(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }

        int[] state = new int[numCourses];
        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (findCyclePath(graph, state, i, path)) {
                    System.out.println("Cycle found: " + path);
                    return;
                }
            }
        }
    }

    private boolean findCyclePath(List<List<Integer>> graph, int[] state,
                                  int course, List<Integer> path) {
        state[course] = 1;
        path.add(course);

        for (int next : graph.get(course)) {
            if (state[next] == 1) {
                // Found cycle, trim path to show only the cycle
                int cycleStart = path.indexOf(next);
                List<Integer> cycle = new ArrayList<>(path.subList(cycleStart, path.size()));
                cycle.add(next);
                path.clear();
                path.addAll(cycle);
                return true;
            }
            if (state[next] == 0) {
                if (findCyclePath(graph, state, next, path)) {
                    return true;
                }
            }
        }

        state[course] = 2;
        path.remove(path.size() - 1);
        return false;
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test Case 1: Multiple valid orders
        System.out.println("Test Case 1: Multiple valid orders");
        int[][] prereq1 = {{1,0}, {2,0}, {3,1}, {3,2}};
        sol.analyzeCourseDependencies(4, prereq1);

        // Test Case 2: Single valid order
        System.out.println("\nTest Case 2: Single valid order (chain)");
        int[][] prereq2 = {{1,0}, {2,1}, {3,2}};
        sol.analyzeCourseDependencies(4, prereq2);

        // Test Case 3: Cycle exists
        System.out.println("\nTest Case 3: Cycle exists");
        int[][] prereq3 = {{1,0}, {0,1}};
        sol.analyzeCourseDependencies(2, prereq3);

        // Test Case 4: No prerequisites
        System.out.println("\nTest Case 4: No prerequisites");
        int[][] prereq4 = {};
        sol.analyzeCourseDependencies(3, prereq4);

        // Test Case 5: Complex dependencies
        System.out.println("\nTest Case 5: Complex dependencies");
        int[][] prereq5 = {{1,0}, {2,0}, {3,1}, {3,2}, {4,3}};
        sol.analyzeCourseDependencies(5, prereq5);

        // Test Case 6: Diamond dependency
        System.out.println("\nTest Case 6: Diamond dependency");
        int[][] prereq6 = {{1,0}, {2,0}, {3,1}, {3,2}};
        sol.analyzeCourseDependencies(4, prereq6);
    }
}