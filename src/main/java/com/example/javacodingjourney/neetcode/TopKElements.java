package com.example.javacodingjourney.neetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKElements {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int num:nums){
            frequencyMap.put(num,frequencyMap.getOrDefault(num,0)+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->frequencyMap.get(a)-frequencyMap.get(b));
        for(int num:frequencyMap.keySet()){
            pq.add(num);
            if(pq.size()>k){
                pq.poll();
            }
        }
        int[] result = new int[k];
        for(int i=0;i<k;i++){
            result[i] = pq.poll();
        }
        return result;
    }
}
