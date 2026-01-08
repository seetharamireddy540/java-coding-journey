package com.example.javacodingjourney.timeandspace;

public class LogarithmicTime {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 5;
        System.out.println("index of 5 -> " + binarySearch(nums, target));
    }

    public static int binarySearch(int[] nums, int target){
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        };
        return -1;
    }
}
