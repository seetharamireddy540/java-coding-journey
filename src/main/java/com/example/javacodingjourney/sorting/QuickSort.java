package com.example.javacodingjourney.sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] data = {10, 7, 8, 9, 1, 5};
        int startIndex = 0;
        int endIndex = data.length - 1;
        System.out.println(Arrays.toString(data));
        quickSort(data, startIndex, endIndex);
        System.out.println(Arrays.toString(data));

    }

    public static void quickSort(int[] data, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(data, startIndex, endIndex);
            quickSort(data, startIndex, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, endIndex);
        }
    }

    public static int partition(int[] data, int startIndex, int endIndex) {

        int pivot = data[endIndex];
        int i = startIndex - 1;
        for (int j = startIndex; j <= endIndex - 1; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[j];
                data[j] = data[i];
                data[i] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[endIndex];
        data[endIndex] = temp;
        return i + 1;
    }
}
