package com.example.neetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given an array of CPU tasks tasks, where tasks[i] is an uppercase english character from A to Z. You are also given an integer n.
 *
 * Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.
 *
 * The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.
 *
 * Return the minimum number of CPU cycles required to complete all tasks.
 */
public class TaskScheduler {

    public static class Task {
        public char task;
        public int frequency;

        public Task(int frequency, char task) {
            this.frequency = frequency;
            this.task = task;
        }
    }

    public static void main(String[] args) {

        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        Map<Character, Integer> frequencyMap = new HashMap<>();
        Deque<int[]> q = new LinkedList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a-b);

        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        for (char task : tasks){
            frequencyMap.put(task, frequencyMap.getOrDefault(task, 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()){
            maxHeap.add(entry.getValue());
        }

        int time = 0;
        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;

            if (maxHeap.isEmpty()) {
                time = q.peek()[1];
            } else {
                int cnt = maxHeap.poll() - 1;
                if (cnt > 0) {
                    q.add(new int[]{cnt, time + n});
                }
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.add(q.poll()[0]);
            }
        }
        System.out.println("Time is -> "+time);
    }
}
