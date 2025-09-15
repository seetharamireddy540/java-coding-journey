package com.example.javacodingjourney.ds;

import java.util.Arrays;

public class PrefixMax {

    public static void main(String[] args) {

        int[] data = {2, 1, 0, 5, 3};
        int[] prefixMax = computePrefixMax(data);
        int[] suffixMax = computeSuffixMax(data);
        int[] prefixSum = computePrefixSumm(data);

        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(prefixMax));
        System.out.println(Arrays.toString(suffixMax));
        System.out.println(Arrays.toString(prefixSum));
    }

    public static int[] computePrefixSumm(int[] data) {
        if (data == null || data.length == 0) {
            return data;
        }
        int[] prefixSum = new int[data.length];
        prefixSum[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + data[i];
        }
        return prefixSum;
    }


    private static int[] computeSuffixMax(int[] data) {
        int length = data.length;
        int[] suffixMax = new int[length];
        suffixMax[length - 1] = data[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(data[i], suffixMax[i + 1]);
        }
        return suffixMax;
    }

    private static int[] computePrefixMax(int[] data) {
        int length = data.length;
        int[] prefixMax = new int[length];
        prefixMax[0] = data[0];
        for (int i = 1; i < length; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], data[i]);
        }
        return prefixMax;
    }
}
