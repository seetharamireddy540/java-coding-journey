package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

public class PermutationsInString {
    public static List<Integer> findPermutations(String S, String B) {
        List<Integer> result = new ArrayList<>();
        int sLen = S.length();
        int bLen = B.length();

        if (sLen > bLen) {
            return result; // No permutations possible if S is longer than B
        }

        // Frequency maps for S and the current window in B
        int[] sCount = new int[26];
        int[] windowCount = new int[26];

        // Initialize frequency map for S
        for (char c : S.toCharArray()) {
            sCount[c - 'a']++;
        }

        // Sliding window over B
        for (int i = 0; i < bLen; i++) {
            // Add current character to the window's frequency map
            windowCount[B.charAt(i) - 'a']++;

            // If window size exceeds S's length, remove the leftmost character
            if (i >= sLen) {
                windowCount[B.charAt(i - sLen) - 'a']--;
            }

            // Compare frequency maps
            if (i >= sLen - 1 && matches(sCount, windowCount)) {
                result.add(i - sLen + 1); // Add starting index of the window
            }
        }

        return result;
    }

    // Helper method to compare two frequency maps
    private static boolean matches(int[] sCount, int[] windowCount) {
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != windowCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String S = "abc";
        String B = "cbaebabacd";
        List<Integer> indices = findPermutations(S, B);

        System.out.println("Starting indices of permutations of S in B:");
        for (int index : indices) {
            System.out.println(index);
        }
    }
}