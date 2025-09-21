package com.example.javacodingjourney.leet;

import java.util.ArrayList;
import java.util.List;

public class SubstringPrinter {

    public static void main(String[] args) {
        String name = "Ram";
        printSubstrings(name);
        System.out.println(permutations(new int[]{1, 2,3}));
    }

    private static void printSubstrings(String name) {
        int n = name.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = name.substring(i, j);
                System.out.println("\"" + substring + "\"");
            }
        }
    }

    public static List<List<Integer>> permutations(int[] numbers) {

        List<List<Integer>> result = new ArrayList<>();

        backtrackHelper(result, new ArrayList<>(), numbers);
        return result;
    }

    /**
     * O(N(N!)) - Time complixty
     * @param result
     * @param tempList
     * @param numbers
     */
    private static void backtrackHelper(List<List<Integer>> result,
                                        ArrayList<Integer> tempList, int[] numbers) {

        if (tempList.size() == numbers.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            //Skip if we get the same number
            if (tempList.contains(numbers[i])) {
                continue;
            }
            tempList.add(numbers[i]);
            backtrackHelper(result, tempList, numbers);
            tempList.removeLast();
        }
    }
}
