package com.example.javacodingjourney.ds;

import java.util.HashMap;
import java.util.Map;

public class LCSArray {

    public static void main(String[] args) {

        int[] input = {0, 5, 0, 1, 0};

        int maxContiguousLength = findMaxLengthOfContiguous(input);
        System.out.println(maxContiguousLength);

    }

    public static int findMaxLengthOfContiguous(int[] input) {

        int sum = 0;
        int maxLength = 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1);
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 1 && input[i] != 0) {
                System.out.println("Invalid input: " + input[i]);
                throw new IllegalArgumentException("Invalid input: " + input[i]);
            } else if (input[i] == 0) {
                input[i] = -1;
            }
        }

        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            if (sumIndexMap.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - sumIndexMap.get(sum));
            } else {
                sumIndexMap.put(sum, i);
            }
        }
        return maxLength;

    }
}
