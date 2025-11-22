package com.example.javacodingjourney.oci;

import java.util.*;
import java.util.*;

class CourseScheduleAllOrders {

    /**
     * BACKTRACKING APPROACH TO FIND ALL POSSIBLE COURSE ORDERS
     *
     * The key idea: At each step, we can choose ANY course that has:
     * 1. Indegree = 0 (all prerequisites completed)
     * 2. Not yet visited
     *
     * We try EACH valid choice and backtrack to try other choices
     */

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

        // Backtracking setup
        List<Integer> currentOrder = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        int[] currentIndegree = indegree.clone();

        System.out.println("Initial state:");
        System.out.println("Indegrees: " + Arrays.toString(currentIndegree));
        System.out.println("Starting backtracking...\n");

        findAllOrdersHelper(graph, currentIndegree, visited, currentOrder,
                allOrders, numCourses, 0);

        return allOrders;
    }

    private void findAllOrdersHelper(List<List<Integer>> graph,
                                     int[] indegree,
                                     boolean[] visited,
                                     List<Integer> currentOrder,
                                     List<List<Integer>> allOrders,
                                     int numCourses,
                                     int depth) {

        // Print current state for visualization
        String indent = "  ".repeat(depth);
        System.out.println(indent + "Level " + depth + ":");
        System.out.println(indent + "Current order: " + currentOrder);
        System.out.println(indent + "Indegrees: " + Arrays.toString(indegree));
        System.out.println(indent + "Visited: " + Arrays.toString(visited));

        // BASE CASE: We've selected all courses
        if (currentOrder.size() == numCourses) {
            allOrders.add(new ArrayList<>(currentOrder));
            System.out.println(indent + "✅ Found complete order: " + currentOrder);
            System.out.println();
            return;
        }

        // RECURSIVE CASE: Try each available course
        System.out.println(indent + "Looking for courses with indegree=0 and not visited...");

        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0 && !visited[course]) {
                System.out.println(indent + "→ Trying course " + course);

                // STEP 1: CHOOSE - Take this course
                visited[course] = true;
                currentOrder.add(course);

                // STEP 2: UPDATE - Reduce indegree of dependent courses
                System.out.println(indent + "  Updating indegrees of courses depending on " + course);
                for (int nextCourse : graph.get(course)) {
                    indegree[nextCourse]--;
                    System.out.println(indent + "    Course " + nextCourse +
                            " indegree: " + (indegree[nextCourse] + 1) +
                            " → " + indegree[nextCourse]);
                }

                // STEP 3: EXPLORE - Recursively find next course
                findAllOrdersHelper(graph, indegree, visited, currentOrder,
                        allOrders, numCourses, depth + 1);

                // STEP 4: BACKTRACK - Undo our choice to try other options
                System.out.println(indent + "← Backtracking from course " + course);

                // Restore indegrees
                for (int nextCourse : graph.get(course)) {
                    indegree[nextCourse]++;
                }

                // Remove course from current order
                currentOrder.remove(currentOrder.size() - 1);
                visited[course] = false;
            }
        }
    }

    /**
     * DETAILED EXAMPLE WITH VISUALIZATION
     *
     * Prerequisites: [[1,0], [2,0], [3,1], [3,2]]
     *
     * Graph structure:
     *      0
     *     / \
     *    1   2
     *     \ /
     *      3
     *
     * Initial indegrees: [0, 1, 1, 2]
     *
     * BACKTRACKING TREE:
     *                     []
     *                     |
     *                  [0] (only 0 has indegree=0)
     *                  / \
     *            [0,1]   [0,2]
     *               |       |
     *          [0,1,2]   [0,2,1]
     *               |       |
     *         [0,1,2,3] [0,2,1,3]
     *
     * Two valid orders found!
     */

    public void demonstrateBacktrackingProcess() {
        System.out.println("=== BACKTRACKING DEMONSTRATION ===\n");

        // Simple example
        System.out.println("Example 1: Simple case with 3 courses");
        System.out.println("Prerequisites: [[2,0], [2,1]]");
        System.out.println("Graph: 0 → 2 ← 1\n");

        int[][] prereq1 = {{2,0}, {2,1}};
        List<List<Integer>> orders1 = findAllPossibleOrders(3, prereq1);

        System.out.println("\n=== SUMMARY ===");
        System.out.println("All possible orders found: " + orders1);
        System.out.println("\nExplanation: We can take 0 and 1 in any order, but 2 must be last");

        System.out.println("\n" + "=".repeat(50) + "\n");

        // More complex example
        System.out.println("Example 2: Diamond dependency");
        System.out.println("Prerequisites: [[1,0], [2,0], [3,1], [3,2]]");
        System.out.println("Graph:");
        System.out.println("    0");
        System.out.println("   / \\");
        System.out.println("  1   2");
        System.out.println("   \\ /");
        System.out.println("    3\n");

        int[][] prereq2 = {{1,0}, {2,0}, {3,1}, {3,2}};
        List<List<Integer>> orders2 = findAllPossibleOrders(4, prereq2);

        System.out.println("\n=== SUMMARY ===");
        System.out.println("All possible orders found: " + orders2);
    }

    /**
     * STEP-BY-STEP TRACE OF THE ALGORITHM
     */
    public void traceAlgorithmStep() {
        System.out.println("=== MANUAL TRACE OF BACKTRACKING ===\n");

        System.out.println("Courses: 0, 1, 2");
        System.out.println("Prerequisites: [[1,0], [2,1]]");
        System.out.println("Graph: 0 → 1 → 2\n");

        System.out.println("Initial state:");
        System.out.println("- Indegrees: [0, 1, 1]");
        System.out.println("- Visited: [false, false, false]");
        System.out.println("- Current order: []\n");

        System.out.println("Step 1: Choose course 0 (only one with indegree=0)");
        System.out.println("- Add 0 to order: [0]");
        System.out.println("- Mark 0 as visited");
        System.out.println("- Reduce indegree of course 1: 1→0");
        System.out.println("- New indegrees: [0, 0, 1]\n");

        System.out.println("Step 2: Choose course 1 (now has indegree=0)");
        System.out.println("- Add 1 to order: [0, 1]");
        System.out.println("- Mark 1 as visited");
        System.out.println("- Reduce indegree of course 2: 1→0");
        System.out.println("- New indegrees: [0, 0, 0]\n");

        System.out.println("Step 3: Choose course 2 (now has indegree=0)");
        System.out.println("- Add 2 to order: [0, 1, 2]");
        System.out.println("- Mark 2 as visited");
        System.out.println("- Order complete! Save [0, 1, 2]\n");

        System.out.println("Step 4: Backtrack from course 2");
        System.out.println("- Remove 2 from order: [0, 1]");
        System.out.println("- Unmark 2 as visited");
        System.out.println("- No more choices at this level\n");

        System.out.println("Step 5: Backtrack from course 1");
        System.out.println("- Remove 1 from order: [0]");
        System.out.println("- Restore indegree of course 2: 0→1");
        System.out.println("- No more choices at this level\n");

        System.out.println("Step 6: Backtrack from course 0");
        System.out.println("- Remove 0 from order: []");
        System.out.println("- Restore indegree of course 1: 0→1");
        System.out.println("- No more choices at root level\n");

        System.out.println("Result: Only one valid order exists: [0, 1, 2]");
    }

    public static void main(String[] args) {
        CourseScheduleAllOrders solution = new CourseScheduleAllOrders();

        // Run demonstration
        solution.demonstrateBacktrackingProcess();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Show manual trace
        solution.traceAlgorithmStep();
    }
}