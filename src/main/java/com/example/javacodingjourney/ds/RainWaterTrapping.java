package com.example.javacodingjourney.ds;



/**
 *
 * Height
 *   4 |           *
 *   3 |     *     *
 *   2 |   * * * * * *
 *   1 | * * * * * * * * *
 *     +-------------------
 *       0 1 2 3 4 5 6 7 8
 * You are given an array of integers which describe elevation at each point in a 2D landscape.
 * For example, the input [ 1 2 3 1 2 4 1 2 1 ] describes a landscape that looks like this:
 * <p>
 * *
 * *     *
 * * *   * *   *
 * * * * * * * * * *
 * [ 1 2 3 1 2 4 1 2 1 ]
 * i 0 1 2 3 4 5 6 7 8
 * <p>
 * Water will fall down on the landscape, filling up anywhere that it's not able to fall off.
 * Find the total "area" of water that would be collected in the landscape.
 * For example, for the input [ 1 2 3 1 2 4 1 2 1 ], the result should be 4, as illustrated by
 * the 4 "/" characters.
 * <p>
 * *
 * * / / *
 * * * / * * / *
 * * * * * * * * * *
 * [ 1 2 3 1 2 4 1 2 1 ]
 * i 0 1 2 3 4 5 6 7 8
 */
public class RainWaterTrapping {

    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
            }
        }

        return water;
    }

    public static int trap(int[] elevation, int[] prefixMax, int[] suffixMax) {
        if (elevation == null || elevation.length == 0) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < elevation.length; i++) {
            if (elevation[i] < prefixMax[i] && elevation[i] < suffixMax[i]) {
                total = total + Math.min(prefixMax[i], suffixMax[i]) - elevation[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[] elevation = {1, 2, 1, 3};
        int[] prefixMax = computePrefixMax(elevation);
        int[] suffixMax = computeSuffixMax(elevation);
        System.out.println(trap(elevation)); // Output: 4
        System.out.println(trap(elevation, prefixMax, suffixMax)); // Output: 4
    }

    private static int[] computeSuffixMax(int[] elevation) {
        int length = elevation.length;
        int[] suffixMax = new int[length];
        suffixMax[length - 1] = elevation[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(elevation[i], suffixMax[i + 1]);
        }
        return suffixMax;
    }

    private static int[] computePrefixMax(int[] elevation) {
        int length = elevation.length;
        int[] prefixMax = new int[length];
        prefixMax[0] = elevation[0];
        for (int i = 1; i < length; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], elevation[i]);
        }
        return prefixMax;
    }
}