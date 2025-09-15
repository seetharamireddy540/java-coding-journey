package com.example.javacodingjourney.sorting;

public class QuickSelect {

    public static void main(String[] args) {
        int[] data = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int startIndex = 0;
        int endIndex = data.length - 1;
        System.out.println(quickSelect(data, startIndex, endIndex, k));
    }

    public static int quickSelect(int[] data, int left, int right, int k) {
        if (left == right) {
            return data[left];
        }
        int pivotIndex = partition(data, left, right);
        if (pivotIndex == k) {
            return data[k];
        }
        if (k < pivotIndex) {
            return quickSelect(data, left, pivotIndex - 1, k);
        } else {
            return quickSelect(data, pivotIndex + 1, right, k);
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
        data[i + 1] = pivot;
        data[endIndex] = temp;
        return i + 1;

    }

}
