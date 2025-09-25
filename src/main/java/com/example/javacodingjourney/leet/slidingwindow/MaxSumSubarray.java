package com.example.javacodingjourney.leet.slidingwindow;

public class MaxSumSubarray {

    public static void main(String[] args) {
        int[] data = {4, 5, 6, -9, 18, 12, 9};
        System.out.println(maxSumSubarray(data, 4));
    }

    public static int maxSumSubarray(int[] data, int k) {
        if (data.length < k) {
            return -1;
        }
        int maxSum = 0;
        int windowSum = 0;
        int bestStartIndex = 0;
        for (int i = 0; i < k; i++) {
            windowSum += data[i];
        }
        maxSum = windowSum;
        for (int right = k; right < data.length; right++) {
            windowSum = windowSum + data[right] - data[right - k];
            if (windowSum > maxSum) {
                maxSum = windowSum;
                bestStartIndex = right - k + 1;
            }
            // maxSum = Math.max(maxSum, windowSum);

        }
        for (int i = bestStartIndex; i< bestStartIndex + k; i++) {
            System.out.print("->" +data[i]);
        }
        System.out.println("-----");
        return maxSum;
    }


}
