package com.example.javacodingjourney.ds;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int start = 0;
        int end = arr.length - 1;

        boolean found = false;
        while (start <= end) {
            int sum = arr[start] + arr[end];
            if (sum == target) {
                found = true;
                System.out.println("start=" + start + " end=" + end);
            }
            if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        if (!found) {
            System.out.println("not found");
        }
    }
}
