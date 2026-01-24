package com.example.javacodingjourney.arrays;

import java.util.Arrays;

public class Basic {

    public static void main(String[] arg) {
        int[] nums = {3, 4, 5, 9, 1, 2, 90};
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        System.out.println("Before ->" + Arrays.toString(nums));
//        reverse(nums, left, right);
//        System.out.println("After ->"+ Arrays.toString(nums));
        System.out.println("After ->" + Arrays.toString(rotate(nums, 3)));

    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        // Step1: Calculate prefix products
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // Step 2 : Calcuate suffic proudct on the fly and multiyp
        int suffixProduct = 1;

        for (int i = n - 1; i >= 0; i--) {
            res[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return res;

    }

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // Left is shorter, so it limits the water
                if (height[left] >= leftMax) leftMax = height[left];
                else totalWater += leftMax - height[left];
                left++;
            } else {
                // Right is shorter or equal, it limits the water
                if (height[right] >= rightMax) rightMax = height[right];
                else totalWater += rightMax - height[right];
                right--;
            }
        }
        return totalWater;
    }
    public static int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k is larger than the array length
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        return nums;
    }
}
