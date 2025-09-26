package com.example.javacodingjourney.leet.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubsetsApp {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = generateAllSubSets(nums);
        System.out.println("All subsets: " + subsets);

    }

    private static List<List<Integer>> generateAllSubSets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;

    }

    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Include number[i]
            current.add(nums[i]);
            // Recurse
            backtrack(nums, i + 1, current, result);
            // Backtrack
            current.removeLast();
        }
    }
}
