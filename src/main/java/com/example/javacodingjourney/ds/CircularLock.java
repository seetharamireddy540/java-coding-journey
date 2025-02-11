package com.example.javacodingjourney.ds;

/**
 * You're trying to open a lock. The lock comes with a wheel which has the integers from
 * 1
 * 1 to
 * N
 * N arranged in a circle in order around it (with integers
 * 1
 * 1 and
 * N
 * N adjacent to one another). The wheel is initially pointing at
 * 1
 * 1.
 * For example, the following depicts the lock for
 * N
 * =
 * 10
 * N=10 (as is presented in the second sample case).
 */
public class CircularLock {
    public static int findMinimumTime(int n, int m, int[] code) {
        int currentPosition = 1;
        int totalTime = 0;

        for (int target : code) {
            // Calculate clockwise and counterclockwise distances
            int clockwise = (target - currentPosition + n) % n;
            int counterClockwise = (currentPosition - target + n) % n;

            // Add the minimum distance to total time
            totalTime += Math.min(clockwise, counterClockwise);

            // Update current position
            currentPosition = target;
        }

        return totalTime;
    }

    public static void main(String[] args) {
        // Example usage
        int n = 10;  // Numbers 1 to 10
        int m = 4;   // Length of code sequence
        int[] code = {9, 4, 4, 8};

        int result = findMinimumTime(n, m, code);
        System.out.println("Minimum time required: " + result + " seconds");
    }
}
