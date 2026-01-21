package com.example.javacodingjourney.alogs.dynamic;

public class LongestIncreasingSubsequence {
    public static class Result {
        int length;
        int[] sequence;

        public Result(int length, int[] sequence) {
            this.length = length;
            this.sequence = sequence;
        }
    }

    public static Result findLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new Result(0, new int[0]);
        }

        int n = arr.length;
        // Length of LIS ending at each index
        int[] lisLength = new int[n];
        // Previous index to reconstruct the sequence
        int[] prevIndex = new int[n];

        // Initialize arrays
        for (int i = 0; i < n; i++) {
            lisLength[i] = 1;
            prevIndex[i] = -1;
        }

        // Maximum length and its ending index
        int maxLength = 1;
        int maxEndIndex = 0;

        // Dynamic Programming approach
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lisLength[i] < lisLength[j] + 1) {
                    lisLength[i] = lisLength[j] + 1;
                    prevIndex[i] = j;
                }
            }

            // Update max length
            if (lisLength[i] > maxLength) {
                maxLength = lisLength[i];
                maxEndIndex = i;
            }
        }

        // Reconstruct the sequence
        int[] subsequence = new int[maxLength];
        for (int i = maxLength - 1; i >= 0; i--) {
            subsequence[i] = arr[maxEndIndex];
            maxEndIndex = prevIndex[maxEndIndex];
        }

        return new Result(maxLength, subsequence);
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        Result result = findLIS(arr);

        System.out.println("Length of Longest Increasing Subsequence: " + result.length);
        System.out.print("Subsequence: ");
        for (int num : result.sequence) {
            System.out.print(num + " ");
        }
    }
}