package com.example.javacodingjourney.oci;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
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
