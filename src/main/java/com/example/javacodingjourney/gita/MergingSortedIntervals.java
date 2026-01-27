package com.example.javacodingjourney.gita;
import java.util.*;

public class MergingSortedIntervals {
    public int[][] merge(int[][] intervals) {
        // If the input is empty, return empty array
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals based on start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // List to store merged intervals
        List<int[]> mergedList = new ArrayList<>();

        // Start with the first interval
        int[] currentInterval = intervals[0];
        mergedList.add(currentInterval);

        // Iterate through the sorted intervals
        for (int[] interval : intervals) {
            // Get the last interval in the merged list
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // If current interval overlaps with next interval
            if (currentEnd >= nextStart) {
                // Merge the intervals by updating the end time
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap, add the new interval
                currentInterval = interval;
                mergedList.add(currentInterval);
            }
        }

        // Convert list to 2D array
        return mergedList.toArray(new int[mergedList.size()][]);
    }

    // Main method to test the solution
    public static void main(String[] args) {
        MergingSortedIntervals solution = new MergingSortedIntervals();
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] merged = solution.merge(intervals);

        // Print merged intervals
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
