package com.example.javacodingjourney.leet;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {
        int[] data = { 5,9, 10, 33, 1,4};
        int target = 14;
        HashMapExample hashMapExample = new HashMapExample();
        System.out.println(Arrays.toString(hashMapExample.twoSum(data, target)));
    }

    public int[] twoSum(int[] data, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            int complement = target - data[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(data[i], i);
        }
        return new int[]{-1, -1};
    }
}
