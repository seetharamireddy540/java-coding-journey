package com.example.javacodingjourney.leet.backtracking;
import java.util.*;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> current = new ArrayList<>();

        backtrack(nums, used, current, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used,
                           List<Integer> current, List<List<Integer>> result) {
        // Base case: permutation complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Choose
                current.add(nums[i]);
                used[i] = true;

                // Explore
                backtrack(nums, used, current, result);

                // Unchoose (backtrack)
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations generator = new Permutations();
        int[] nums = {1, 2, 3};
        List<List<Integer>> perms = generator.permute(nums);

        System.out.println("All permutations: " + perms);
        // Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
    }
}
