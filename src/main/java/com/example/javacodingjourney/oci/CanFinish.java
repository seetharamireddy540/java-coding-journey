package com.example.javacodingjourney.oci;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanFinish {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] prereq1 = {{1, 0}};
        int numberOfCourses = 2;
        // Test Case 1: Multiple valid orders
        System.out.println("Test Case 1: Multiple valid orders");
        int[][] prereq2 = {{1,0}, {2,0}, {3,1}, {3,2}};
        solution.findOrderDFS(4, prereq1);
    }

    public static class Solution {

        public int[] findOrderDFS(int numberOfCourses, int[][] prerequisites) {

            List<List<Integer>> graph = new LinkedList<>();
            //Build graph
            for (int i = 0; i < numberOfCourses; i++) {
                graph.add(new ArrayList<>());
            }
            // Build graph with course -> pre-requisite
            for (int[] prereq : prerequisites) {
                graph.get(prereq[0]).add(prereq[1]);

            }
            // 0 ->unvisited, 1 visiting 2 means visited
            int[] state = new int[numberOfCourses];
            Deque<Integer> stack = new ArrayDeque<>();
            // Try vising all courses
            for (int i = 0; i < numberOfCourses; i++) {
                if (state[i] == 0) {
                    if (hasCycleDFS(graph, state, i, stack)) {
                        return new int[0];
                    }
                }
            }

            // Build result from stack (Rever order)

            int[] result = new int[numberOfCourses];
            int index = 0;
            while (!stack.isEmpty()) {
                result[index++] = stack.pop();
            }

            return result;
        }

        private boolean hasCycleDFS(List<List<Integer>> graph, int[] state, int course, Deque<Integer> stack) {
            state[course] = 1;
            for (int nextCourse : graph.get(course)) {
                if (state[nextCourse] == 1) {
                    return true; // Cycle deted
                }
                if (state[nextCourse] == 0) {
                    if (hasCycleDFS(graph, state, nextCourse, stack)) {
                        return true;
                    }
                }
            }
            state[course] = 2;
            stack.push(course);
            return false;
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {

            List<List<Integer>> graph = new ArrayList<>();

            // Build empty graph
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }

            // Build adjacency list with prerequisit - reverse graph
            int[] indegree = new int[numCourses];
            for (int[] prereq : prerequisites) {
                int course = prereq[0];
                int prerequisite = prereq[1];
                graph.get(prerequisite).add(course);
                indegree[course]++;
            }

            //Find all course with no prerequisites
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            // Process courses and build order
            int[] coursesOrder = new int[numCourses];
            int index = 0;
            while (!queue.isEmpty()) {
                int course = queue.poll();
                coursesOrder[index++] = course;
                // Process dependency courses
                for (int nextCourse : graph.get(course)) {
                    indegree[nextCourse]--;
                    if (indegree[nextCourse] == 0) {
                        queue.offer(nextCourse);
                    }
                }
            }
            if (index == numCourses) {
                return coursesOrder;
            } else {
                return new int[]{-1};
            }
        }
    }
}
