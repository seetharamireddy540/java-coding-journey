package com.example.javacodingjourney.ds;

import java.util.ArrayList;
import java.util.List;

public class ArrayPermutations {


    public static void permute(int[] arr, List<List<Integer>> result, boolean[] isUsed, List<Integer> permutation) {

        if (permutation.size() == arr.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                permutation.add(arr[i]);
                permute(arr, result, isUsed, permutation);
                isUsed[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    public static void printPermutations(int[] arr, int index) {
        if (index == arr.length) {
            printArray(arr);
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            printPermutations(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
       // printPermutations(arr, 0);
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        boolean[] isUsed = new boolean[arr.length];
        permute(arr, result, isUsed, permutation);
        result.forEach(permutation1 -> System.out.println(permutation1));

    }
}
