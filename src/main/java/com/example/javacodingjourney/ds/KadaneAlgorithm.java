package com.example.javacodingjourney.ds;

public class KadaneAlgorithm {

    public static void main(String[] args) {

        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is: " + maxSubArraySum(arr));
        Result result = maxSubArraySumWithArray(arr);

        System.out.println("Maximum Sum: " + result.maxSum);
        System.out.println("Start Index: " + result.start);
        System.out.println("End Index: " + result.end);
        printSubarray(arr, result.start, result.end);

    }

    public static void printSubarray(int[] arr, int start, int end) {
        System.out.print("Subarray elements: ");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static Result maxSubArraySumWithArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new Result(0, -1, -1);
        }

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere = maxEndingHere + arr[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new Result(maxSoFar, start, end);
    }

    private static int maxSubArraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;

        }
        int maxSum = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSum = Math.max(maxSum, maxEndingHere);
        }

        return maxSum;
    }

    static class Result {
        int maxSum;
        int start;
        int end;

        Result(int maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }
    }
}
