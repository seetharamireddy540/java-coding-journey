package com.example.javacodingjourney.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Time complexity - O(n)
 * and Space complexity - O(n)
 */
public class Anagram {

    public static void main(String[] args) {
        String str1 = "danger";
        String str2 = "garden";
        System.out.println(" " + str1 + " And " + str2 + " are anagram. ->" + isAnagrams(str1, str2));
        String t= "test";
        String s = "est";
        System.out.println(" " + t + " And " + s + " are anagram. ->" + isAngramArrays(t, s));
    }

    public static boolean isAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char ch1 = str1.charAt(i);
            map1.put(ch1, map1.getOrDefault(ch1, 0) + 1);
        }

        for (int i = 0; i < str2.length(); i++) {
            char ch2 = str2.charAt(i);
            map2.put(ch2, map2.getOrDefault(ch2, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            if (!map2.containsKey(entry.getKey()) || !map2.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAngramArrays(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return Arrays.equals(ch1, ch2);

    }

}
