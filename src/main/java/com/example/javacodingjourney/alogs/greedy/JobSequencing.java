package com.example.javacodingjourney.alogs.greedy;

import java.util.Arrays;

class JobSequencing {
    public static int[] scheduleJobs(Job[] jobs, int maxDeadline) {
        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int[] result = new int[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];

        for (Job job : jobs) {
            // Find available slot close to deadline
            for (int j = Math.min(maxDeadline - 1, job.deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = job.id;
                    slot[j] = true;
                    break;
                }
            }
        }

        return result;
    }

    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}