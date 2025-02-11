package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class KClosestElementsTwoPointer {
    public static List<Integer> findKClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return result;
        }

        int left = 0;
        int right = arr.length - 1;

        // Shrink the window until we have k elements
        while (right - left >= k) {
            int distLeft = Math.abs(arr[left] - x);
            int distRight = Math.abs(arr[right] - x);

            if (distLeft > distRight) {
                left++;
            } else {
                right--;
            }
        }

        // Add all elements from left to right to result
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int x = 5;

        List<Integer> closest = findKClosestElements(arr, k, x);
        System.out.println("K closest elements to " + x + ": " + closest);
    }
}
