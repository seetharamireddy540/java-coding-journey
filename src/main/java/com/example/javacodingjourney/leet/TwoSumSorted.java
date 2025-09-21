package com.example.javacodingjourney.leet;

import java.util.Arrays;

public class TwoSumSorted {

    public static void main(String[] args) {
        int[] data = {4, 5, 7, 8, 9, 10};
        int target = 15;
        System.out.println("result ->" + Arrays.toString(twoSum(data, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
