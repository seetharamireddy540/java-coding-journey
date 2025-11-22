package com.example.javacodingjourney.oci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Given an array of integers and a target sum, return indices of two numbers that add up to the target.
 * <p>
 * Create file
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] input = {4, 8, 9, 1, 5, 6};
        int target = 10;
        int[] result = twoSum(input, target);
        System.out.println(Arrays.toString(result));
        result = twoSum(input, 22);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

}
