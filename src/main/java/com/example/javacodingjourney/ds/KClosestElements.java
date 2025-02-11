package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Problem specifications:
 Given a sorted array and target value x
 Find k closest values to the target
 Return result in ascending order

 * How It Works
 * Binary Search Approach
 * Initialize two pointers (left and right)
 * Use binary search to find the starting point of k closest elements
 * Compare differences between x and elements at mid and mid+k
 * Narrow down the search range based on comparison
 * Time Complexity
 * Binary Search: O(log(n-k))
 * Creating result list: O(k)
 * Overall: O(log(n-k) + k)
 * Space Complexity
 * O(k) for storing the result
 */
public class KClosestElements {
    public static List<Integer> findKClosestElements(int[] arr, int k, int x) {
        // Initialize result list
        List<Integer> result = new ArrayList<>();

        // If array is empty or k is 0
        if (arr == null || arr.length == 0 || k == 0) {
            return result;
        }

        // Find the closest element using binary search
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare difference between x and elements at mid and mid+k
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Add k elements to result starting from left index
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int x = 5;

        List<Integer> closest = findKClosestElements(arr, k, x);
        System.out.println("K closest elements to " + x + ": " + closest);
    }
}
