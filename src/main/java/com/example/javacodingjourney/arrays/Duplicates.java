package com.example.javacodingjourney.arrays;

import java.util.HashMap;
import java.util.HashSet;

public class Duplicates {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 10, 3, 4};

        if (hasDups(nums)) {

            System.out.println("Given arrays has duplicates!!!");
        } else {
            System.out.println("No duplicates founds");
        }
    }

    /**
     * Time complexity - O(n2)
     */
    private static boolean checkDuplicates(int[] nums) {
        boolean isDupExists = false;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    isDupExists = true;
                }
            }
        }
        return isDupExists;
    }

    /**
     * Time complexity - O(n)
     * and Space complexity - O(n)
     */
    private static boolean checkDuplicatesUsingHashMap(int[] nums) {
        Boolean hasDups = false;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        for (int key : hashMap.keySet()) {
            if (hashMap.get(key) > 1) {
                System.out.println("Duplicates element is:  " + key);
                hasDups = true;
            }
        }
        return hasDups;
    }

    /**
     * Time complexity - O(n)
     * and Space complexity - O(n)
     */
    private static boolean hasDups(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        boolean hasDups = false;
        for (int num : nums) {
            if (hashSet.contains(num)) {
                hasDups = true;
            } else {
                hashSet.add(num);
            }
        }
        return hasDups;
    }
}
