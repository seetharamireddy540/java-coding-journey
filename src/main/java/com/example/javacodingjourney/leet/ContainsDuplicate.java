package com.example.javacodingjourney.leet;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(containsNearByDuplicate(nums, k));
    }

    public static boolean containsNearByDuplicate(int[] nums, int k) {
        Map<Integer, Integer> tempMap = new HashMap<>();
        if (nums.length == 1) return false;

        for (int i = 0; i < nums.length; i++) {

            if (tempMap.containsKey(nums[i])) {
                int temp = tempMap.get(nums[i]);
                if (Math.abs(i - temp) <= k) {
                    return true;
                } else {
                    tempMap.put(nums[i], i);
                }
            } else {
                tempMap.put(nums[i], i);
            }
        }
        return false;
    }
}
