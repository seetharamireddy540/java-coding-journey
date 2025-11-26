package com.example.javacodingjourney.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        int[] data = {2, 5, 8, 1, 0, 7};
        System.out.println(Arrays.toString(data));
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void insertionSort(int[] data) {

        for (int i = 1; i < data.length; i++) {
            int current = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > current) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = current;
        }
    }
}
