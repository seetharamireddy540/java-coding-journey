package com.example.javacodingjourney.oci;

import java.util.*;

public class BacktrackingTutorial {

    /**
     * ==========================================
     * LESSON 1: WHAT IS BACKTRACKING?
     * ==========================================
     *
     * Backtracking is like exploring a maze:
     * 1. You try a path
     * 2. If it leads to dead end, you go back
     * 3. Try a different path
     * 4. Repeat until you find the exit (or all exits)
     *
     * TEMPLATE:
     * backtrack(state) {
     *     if (base case) {
     *         save/print solution
     *         return
     *     }
     *
     *     for (each choice) {
     *         if (valid choice) {
     *             make choice
     *             backtrack(new state)
     *             undo choice  // This is the "backtrack" step!
     *         }
     *     }
     * }
     */

    // ==========================================
    // PROBLEM 1: PRINT ALL BINARY STRINGS OF LENGTH N
    // ==========================================
    static class BinaryStrings {
        /**
         * Generate all binary strings of length n
         * Example: n=2 → ["00", "01", "10", "11"]
         *
         * Decision tree for n=3:
         *                    ""
         *                   /  \
         *                  0    1
         *                 / \  / \
         *               00 01 10 11
         *              /\ /\ /\ /\
         *            000...    ...111
         */

        public static List<String> generateBinaryStrings(int n) {
            List<String> result = new ArrayList<>();
            backtrack(n, "", result, 0);
            return result;
        }

        private static void backtrack(int n, String current, List<String> result, int depth) {
            // Visualization
            System.out.println("  ".repeat(depth) + "Current: '" + current + "'");

            // Base case: we've made n decisions
            if (current.length() == n) {
                result.add(current);
                System.out.println("  ".repeat(depth) + "✓ Found solution: " + current);
                return;
            }

            // Try choice 1: add '0'
            System.out.println("  ".repeat(depth) + "→ Trying '0'");
            backtrack(n, current + "0", result, depth + 1);

            // Try choice 2: add '1'
            System.out.println("  ".repeat(depth) + "→ Trying '1'");
            backtrack(n, current + "1", result, depth + 1);

            // Notice: No explicit "undo" needed because String is immutable
        }

        public static void demo() {
            System.out.println("=== BINARY STRINGS (n=3) ===\n");
            List<String> result = generateBinaryStrings(3);
            System.out.println("\nAll binary strings: " + result);
            System.out.println("Total: " + result.size() + " (2^3 = 8)");
        }
    }

    // ==========================================
    // PROBLEM 2: GENERATE ALL SUBSETS
    // ==========================================
    static class Subsets {
        /**
         * Generate all subsets of [1,2,3]
         * For each element, we have 2 choices: include it or not
         *
         * Decision tree:
         *                    []
         *              /           \
         *            []            [1]        (include 1?)
         *          /    \        /     \
         *        []     [2]    [1]    [1,2]   (include 2?)
         *       / \    / \    / \     / \
         *      [] [3] [2][2,3][1][1,3][1,2][1,2,3] (include 3?)
         */

        public static List<List<Integer>> generateSubsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            backtrack(nums, 0, current, result);
            return result;
        }

        private static void backtrack(int[] nums, int index,
                                      List<Integer> current,
                                      List<List<Integer>> result) {
            // Base case: we've made decision for all elements
            if (index == nums.length) {
                result.add(new ArrayList<>(current));
                System.out.println("Found subset: " + current);
                return;
            }

            // Choice 1: DON'T include nums[index]
            System.out.println("  ".repeat(index) + "Skip " + nums[index] +
                    ", current: " + current);
            backtrack(nums, index + 1, current, result);

            // Choice 2: DO include nums[index]
            current.add(nums[index]);
            System.out.println("  ".repeat(index) + "Take " + nums[index] +
                    ", current: " + current);
            backtrack(nums, index + 1, current, result);

            // BACKTRACK: undo the choice
            current.remove(current.size() - 1);
            System.out.println("  ".repeat(index) + "Backtrack, removed " +
                    nums[index] + ", current: " + current);
        }

        public static void demo() {
            System.out.println("\n=== SUBSETS of [1,2,3] ===\n");
            int[] nums = {1, 2, 3};
            List<List<Integer>> result = generateSubsets(nums);
            System.out.println("\nAll subsets: " + result);
            System.out.println("Total: " + result.size() + " (2^3 = 8)");
        }
    }

    // ==========================================
    // PROBLEM 3: PERMUTATIONS
    // ==========================================
    static class Permutations {
        /**
         * Generate all permutations of [1,2,3]
         * At each step, we can choose any unused element
         *
         * Decision tree:
         *                    []
         *               /    |    \
         *             [1]   [2]   [3]
         *            / \    / \    / \
         *         [1,2][1,3][2,1][2,3][3,1][3,2]
         *           |   |    |    |    |    |
         *        [1,2,3]......................[3,2,1]
         */

        public static List<List<Integer>> generatePermutations(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            backtrack(nums, current, used, result, 0);
            return result;
        }

        private static void backtrack(int[] nums, List<Integer> current,
                                      boolean[] used, List<List<Integer>> result,
                                      int depth) {
            System.out.println("  ".repeat(depth) + "Current: " + current +
                    ", Used: " + Arrays.toString(used));

            // Base case: we have a complete permutation
            if (current.size() == nums.length) {
                result.add(new ArrayList<>(current));
                System.out.println("  ".repeat(depth) + "✓ Found permutation: " + current);
                return;
            }

            // Try each unused number
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;  // Skip used numbers

                // CHOOSE
                System.out.println("  ".repeat(depth) + "→ Choose " + nums[i]);
                current.add(nums[i]);
                used[i] = true;

                // EXPLORE
                backtrack(nums, current, used, result, depth + 1);

                // BACKTRACK (undo)
                current.remove(current.size() - 1);
                used[i] = false;
                System.out.println("  ".repeat(depth) + "← Backtrack from " + nums[i]);
            }
        }

        public static void demo() {
            System.out.println("\n=== PERMUTATIONS of [1,2,3] ===\n");
            int[] nums = {1, 2, 3};
            List<List<Integer>> result = generatePermutations(nums);
            System.out.println("\nAll permutations: " + result);
            System.out.println("Total: " + result.size() + " (3! = 6)");
        }
    }

    // ==========================================
    // PROBLEM 4: N-QUEENS (Classic Backtracking)
    // ==========================================
    static class NQueens {
        /**
         * Place N queens on NxN board so no two queens attack each other
         * Queens attack horizontally, vertically, and diagonally
         */

        public static List<List<String>> solveNQueens(int n) {
            List<List<String>> solutions = new ArrayList<>();
            char[][] board = new char[n][n];

            // Initialize board with '.'
            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], '.');
            }

            backtrack(board, 0, solutions);
            return solutions;
        }

        private static void backtrack(char[][] board, int row,
                                      List<List<String>> solutions) {
            // Base case: all queens placed successfully
            if (row == board.length) {
                solutions.add(constructSolution(board));
                printBoard(board);
                return;
            }

            // Try placing queen in each column of current row
            for (int col = 0; col < board.length; col++) {
                if (isSafe(board, row, col)) {
                    // CHOOSE
                    board[row][col] = 'Q';
                    System.out.println("Place queen at (" + row + "," + col + ")");

                    // EXPLORE
                    backtrack(board, row + 1, solutions);

                    // BACKTRACK
                    board[row][col] = '.';
                    System.out.println("Remove queen from (" + row + "," + col + ")");
                }
            }
        }

        private static boolean isSafe(char[][] board, int row, int col) {
            int n = board.length;

            // Check column
            for (int i = 0; i < row; i++) {
                if (board[i][col] == 'Q') return false;
            }

            // Check upper left diagonal
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') return false;
            }

            // Check upper right diagonal
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 'Q') return false;
            }

            return true;
        }

        private static List<String> constructSolution(char[][] board) {
            List<String> result = new ArrayList<>();
            for (char[] row : board) {
                result.add(new String(row));
            }
            return result;
        }

        private static void printBoard(char[][] board) {
            System.out.println("\nFound solution:");
            for (char[] row : board) {
                System.out.println(new String(row));
            }
            System.out.println();
        }

        public static void demo() {
            System.out.println("\n=== 4-QUEENS PROBLEM ===\n");
            List<List<String>> solutions = solveNQueens(4);
            System.out.println("Total solutions: " + solutions.size());
        }
    }

    // ==========================================
    // PROBLEM 5: COMBINATION SUM
    // ==========================================
    static class CombinationSum {
        /**
         * Find all combinations that sum to target
         * Can use same number multiple times
         * Example: candidates=[2,3,6,7], target=7
         * Output: [[2,2,3], [7]]
         */

        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            backtrack(candidates, target, 0, current, result, 0);
            return result;
        }

        private static void backtrack(int[] candidates, int target, int start,
                                      List<Integer> current, List<List<Integer>> result,
                                      int depth) {
            System.out.println("  ".repeat(depth) + "Current: " + current +
                    ", Sum: " + current.stream().mapToInt(Integer::intValue).sum() +
                    ", Target: " + target);

            // Base cases
            if (target == 0) {
                result.add(new ArrayList<>(current));
                System.out.println("  ".repeat(depth) + "✓ Found combination: " + current);
                return;
            }

            if (target < 0) {
                System.out.println("  ".repeat(depth) + "✗ Exceeded target");
                return;
            }

            // Try each candidate from start index
            for (int i = start; i < candidates.length; i++) {
                // CHOOSE
                System.out.println("  ".repeat(depth) + "→ Try " + candidates[i]);
                current.add(candidates[i]);

                // EXPLORE (can reuse same number, so pass i not i+1)
                backtrack(candidates, target - candidates[i], i, current, result, depth + 1);

                // BACKTRACK
                current.remove(current.size() - 1);
                System.out.println("  ".repeat(depth) + "← Backtrack from " + candidates[i]);
            }
        }

        public static void demo() {
            System.out.println("\n=== COMBINATION SUM ===");
            System.out.println("Candidates: [2,3,6,7], Target: 7\n");
            int[] candidates = {2, 3, 6, 7};
            List<List<Integer>> result = combinationSum(candidates, 7);
            System.out.println("\nAll combinations: " + result);
        }
    }

    // ==========================================
    // MAIN METHOD - RUN ALL DEMOS
    // ==========================================
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     BACKTRACKING TUTORIAL              ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Run demonstrations
        BinaryStrings.demo();
        System.out.println("\n" + "=".repeat(50));

        Subsets.demo();
        System.out.println("\n" + "=".repeat(50));

        Permutations.demo();
        System.out.println("\n" + "=".repeat(50));

        NQueens.demo();
        System.out.println("\n" + "=".repeat(50));

        CombinationSum.demo();

        // Print summary
        printBacktrackingSummary();
    }

    private static void printBacktrackingSummary() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\n📚 BACKTRACKING SUMMARY:");
        System.out.println("\n1. PATTERN:");
        System.out.println("   - Make a choice");
        System.out.println("   - Explore consequences");
        System.out.println("   - Undo the choice (backtrack)");

        System.out.println("\n2. WHEN TO USE:");
        System.out.println("   - Find ALL solutions");
        System.out.println("   - Optimization problems");
        System.out.println("   - Decision problems");
        System.out.println("   - When you need to explore possibilities");

        System.out.println("\n3. KEY COMPONENTS:");
        System.out.println("   - Base case (when to stop)");
        System.out.println("   - Choice space (what options available)");
        System.out.println("   - Constraints (what's valid)");
        System.out.println("   - State management (track progress)");

        System.out.println("\n4. TIME COMPLEXITY:");
        System.out.println("   - Usually exponential O(b^d)");
        System.out.println("   - b = branching factor");
        System.out.println("   - d = depth of recursion");

        System.out.println("\n5. OPTIMIZATION TIPS:");
        System.out.println("   - Prune early (check constraints ASAP)");
        System.out.println("   - Order choices (try most likely first)");
        System.out.println("   - Use memoization when possible");
    }
}