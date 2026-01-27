package com.example.javacodingjourney.gita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Example usage
public class StreamIntervalsDemo {
    public static void main(String[] args) {
        IntervalMerger merger = new IntervalMerger();

        // Simulating stream of intervals
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        for (int[] interval : intervals) {
            merger.addInterval(interval);
        }

        // Get merged intervals
        List<int[]> mergedIntervals = merger.getMergedIntervals();

        // Print merged intervals
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }

    public static  class IntervalMerger {
        // Priority queue to store intervals
        private PriorityQueue<int[]> intervalQueue;

        public IntervalMerger() {
            // Min-heap based on start times
            intervalQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }

        // Method to add a new interval to the stream
        public void addInterval(int[] newInterval) {
            intervalQueue.offer(newInterval);
        }

        // Method to get merged intervals
        public List<int[]> getMergedIntervals() {
            List<int[]> mergedIntervals = new ArrayList<>();

            // If no intervals, return empty list
            if (intervalQueue.isEmpty()) {
                return mergedIntervals;
            }

            // Start with the first interval
            int[] current = intervalQueue.poll();

            // Process remaining intervals
            while (!intervalQueue.isEmpty()) {
                int[] next = intervalQueue.poll();

                // Check for overlap
                if (current[1] >= next[0]) {
                    // Merge intervals
                    current[1] = Math.max(current[1], next[1]);
                } else {
                    // Add non-overlapping interval
                    mergedIntervals.add(current);
                    current = next;
                }
            }

            // Add the last interval
            mergedIntervals.add(current);

            return mergedIntervals;
        }
    }

}