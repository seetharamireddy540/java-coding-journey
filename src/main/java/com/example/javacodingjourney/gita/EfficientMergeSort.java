package com.example.javacodingjourney.gita;

import java.util.Arrays;

public class EfficientMergeSort {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original Array: " + Arrays.toString(arr));

        mergeSort(arr);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    // Main merge sort method without extra memory
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        // Find the middle index
        int mid = arr.length / 2;

        // Create two halves
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Populate left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        // Recursive sort
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(arr, left, right);
    }

    // Efficient merge method
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare and merge
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Handle remaining elements in left array
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Handle remaining elements in right array
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}