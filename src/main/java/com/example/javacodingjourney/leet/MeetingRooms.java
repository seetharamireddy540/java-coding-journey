package com.example.javacodingjourney.leet;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

    public static void main(String[] args) {

        int[][] meetings = {{0, 30}, {5, 25}, {15, 20}};
        System.out.println("Minimum number of meeting rooms required: " + minMeetingRooms(meetings));
    }

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        rooms.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (rooms.peek() != null && intervals[i][0] >= rooms.peek()) {
                rooms.poll();
            }
            rooms.add(intervals[i][1]);
        }

        return rooms.size();
    }
}
