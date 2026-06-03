package com.example.javacodingjourney.neetcode;

import java.util.HashSet;
import java.util.Set;

public class FindingDuplicates {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        boolean res = checkDuplicates(nums);

        System.out.println(res);

    }

    /**
     * Time Complexity: O(n)
     * Space Complexity : O(n)
     * @param nums
     * @return
     */
    public static boolean checkDuplicates(int[] nums) {
        final Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
