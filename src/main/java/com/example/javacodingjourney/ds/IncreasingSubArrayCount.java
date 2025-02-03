package com.example.javacodingjourney.ds;

/**
 * Given an array of positive integers, the task is to count how many subarrays are strictly increasing. A strictly increasing subarray is one where each element is greater than the previous element1.
 */
public class IncreasingSubArrayCount {

    public static void main(String[] args) {

        int data[] = {1, 2, 3, 4};
        int data2[] = {4, 3, 2, 1};
        int subArrayCount = countIncreasingSubArrayCount(data);
        int subArrayCount2 = countDecreasingSubarrays(data2);
        System.out.println("Increasing Subarray Count: " + subArrayCount);
        System.out.println("Decreasing Subarray Count: " + subArrayCount2);
    }

    /**
     * This algorithm runs in O(n) where n is the length of the input array.
     * Space complexity is O(1) as we only use a constant amount of extra array.
     * @param data
     * @return
     */
    private static int countIncreasingSubArrayCount(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        int startIndex = 0;
        int subArrayCount = 0;
        while (startIndex < data.length) {
            int endIndex = startIndex + 1;
            while (endIndex < data.length && data[endIndex] > data[endIndex - 1]) {
                endIndex++;
            }
            int length = endIndex - startIndex;
            subArrayCount = subArrayCount + (length * (length + 1)) / 2;
            startIndex = endIndex;

        }
        return subArrayCount;
    }

    private static int countDecreasingSubarrays(int[] nums) {
        int totalSubarrays = 0;
        int startIdx = 0;
        int arraySize = nums.length;

        while (startIdx < arraySize) {
            int endIdx = startIdx + 1;

            while (endIdx < arraySize && nums[endIdx] < nums[endIdx - 1]) {
                endIdx++;
            }
            long currentSubarrayCount = endIdx - startIdx;
            totalSubarrays += (currentSubarrayCount + 1) * currentSubarrayCount / 2;
            startIdx = endIdx;
        }

        return totalSubarrays;
    }

    public long countMonotonicSubarrays(int[] nums) {
        long totalSubarrays = 0;
        int startIdx = 0;
        int arraySize = nums.length;

        while (startIdx < arraySize - 1) {
            int endIdx = startIdx + 1;

            // Check if sequence is increasing
            if (nums[endIdx] > nums[startIdx]) {
                while (endIdx < arraySize && nums[endIdx] > nums[endIdx - 1]) {
                    endIdx++;
                }
            }
            // Check if sequence is decreasing
            else if (nums[endIdx] < nums[startIdx]) {
                while (endIdx < arraySize && nums[endIdx] < nums[endIdx - 1]) {
                    endIdx++;
                }
            }
            // If equal, move to next element
            else {
                startIdx++;
                continue;
            }

            long currentSubarrayCount = endIdx - startIdx;
            totalSubarrays += (currentSubarrayCount + 1) * currentSubarrayCount / 2;
            startIdx = endIdx;
        }

        // Don't forget to count single-element subarrays
        return totalSubarrays + nums.length;
    }
}
