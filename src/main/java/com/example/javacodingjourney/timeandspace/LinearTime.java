package com.example.javacodingjourney.timeandspace;
public class LinearTime {
    // Simple array traversal
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {  // O(n) - visits each element once
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Linear search
    public static boolean contains(int[] arr, int target) {
        for (int num : arr) {  // O(n) - worst case checks all elements
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}