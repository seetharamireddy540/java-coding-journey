package com.example.javacodingjourney.alogs.greedy;

import java.util.Arrays;

class ActivitySelection {
    public static int maxActivities(int[] start, int[] end) {
        // Create activity pairs and sort by end time
        Activity[] activities = new Activity[start.length];
        for (int i = 0; i < start.length; i++) {
            activities[i] = new Activity(start[i], end[i]);
        }

        // Sort activities by end time
        Arrays.sort(activities, (a, b) -> a.endTime - b.endTime);

        int count = 1;
        int lastEndTime = activities[0].endTime;

        // Select non-overlapping activities
        for (int i = 1; i < activities.length; i++) {
            if (activities[i].startTime >= lastEndTime) {
                count++;
                lastEndTime = activities[i].endTime;
            }
        }

        return count;
    }

    static class Activity {
        int startTime;
        int endTime;

        Activity(int start, int end) {
            this.startTime = start;
            this.endTime = end;
        }
    }
}