package com.example.javacodingjourney.alogs.greedy;

import java.util.Arrays;

/**
 * Approach Steps:
 *
 * Identify if problem has greedy choice property
 * Prove greedy choice is safe
 * Implement locally optimal selection
 * Build solution incrementally
 * Verify global optimum
 * Common Greedy Problem Patterns:
 *
 * Sorting-based solutions
 * Minimizing/maximizing something
 * Making optimal local choices
 * Handling constraints efficiently
 * Problem Recognition Techniques
 * Look for optimization problems
 * Check if local optimal choice leads to global solution
 * Identify incremental decision-making scenarios
 * Complexity Analysis
 * Time Complexity: Usually O(n log n) due to sorting
 * Space Complexity: O(1) or O(n)
 * Limitations of Greedy Algorithms
 * Not always produce optimal solution
 * Work only for problems with greedy choice property
 * Require careful problem analysis
 * Interview Tips
 * Explain your greedy choice
 * Prove why local optimal leads to global optimal
 * Discuss alternative approaches
 * Handle edge cases
 * Optimize space and time complexity
 * Sample Interview Questions:
 *
 * Minimum Platforms Required
 * Huffman Coding
 * Water Connection Problem
 * Chocolate Distribution
 * Minimize Cash Flow
 * Minimum Spanning Tree
 * Dijkstra's Shortest Path
 */
public class GreedyAlgorithmTemplate {
    public static int greedyApproach(int[] input) {
        // Sort input if required
        Arrays.sort(input);

        int result = 0;

        // Make greedy choices
        for (int item : input) {
            // Decision making logic
            if (canTake(item)) {
                result += processItem(item);
            }
        }

        return result;
    }

    private static boolean canTake(int item) {
        // Constraint checking
        return true;
    }

    private static int processItem(int item) {
        // Process and return value
        return item;
    }
}