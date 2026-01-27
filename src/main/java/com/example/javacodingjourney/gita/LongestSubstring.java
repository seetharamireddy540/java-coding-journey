package com.example.javacodingjourney.gita;

import java.util.HashSet;

public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println("Hello");
        String  s = "abcabcbbced";
        System.out.println(longestSubstring(s));
    }

    private static String longestSubstring(String s) {

        if(s.length()== 0 ||s.length()==1){
            return s;
        }

        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        int startIndex = 0;
        for(int right = 0; right<s.length(); right++){
            char ch = s.charAt(right);
            while(set.contains(ch)){
                set.remove(s.charAt(left));
                left++;
            }
                set.add(ch);

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                startIndex = left;
            }
        }

        // Return substring
        return s.substring(startIndex, startIndex + maxLength);
        }

    }


