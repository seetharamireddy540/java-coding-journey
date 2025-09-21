package com.example.javacodingjourney.leet;

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {

    public static void main(String[] args) {
        String data = "Mittala";
        System.out.println("result ->" + lengthOfLongestSubstring(data));
    }

    private static int lengthOfLongestSubstring(String data) {
        Set<Character> tempSet = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLength = 0;
        for (right = 0; right < data.length(); right++) {

            while (tempSet.contains(data.charAt(right))) {
                tempSet.remove(data.charAt(left));
                left++;
            }
            tempSet.add(data.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
