package com.example.javacodingjourney.ds;

/**
 * You are given an array of integers which describe elevation at each point in a 2D landscape.
 * For example, the input [ 1 2 3 1 2 4 1 2 1 ] describes a landscape that looks like this:
 *
 *             *
 *       *     *
 *     * *   * *   *
 *   * * * * * * * * *
 * [ 1 2 3 1 2 4 1 2 1 ]
 * i 0 1 2 3 4 5 6 7 8
 *
 * Water will fall down on the landscape, filling up anywhere that it's not able to fall off.
 * Find the total "area" of water that would be collected in the landscape.
 * For example, for the input [ 1 2 3 1 2 4 1 2 1 ], the result should be 4, as illustrated by
 * the 4 "/" characters.
 *
 *             *
 *       * / / *
 *     * * / * * / *
 *   * * * * * * * * *
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

    public static void main(String[] args) {
        int[] elevation = {1, 2, 3, 1, 2, 4, 1, 2, 1};
        System.out.println(trap(elevation)); // Output: 4
    }
}