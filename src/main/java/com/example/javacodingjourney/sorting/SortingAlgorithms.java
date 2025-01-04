package com.example.javacodingjourney.sorting;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        // Bubble Sort
        bubbleSort(array.clone());

        // Selection Sort
        selectionSort(array.clone());

        // Insertion Sort
        insertionSort(array.clone());

        // Merge Sort
        mergeSort(array.clone());

        // Quick Sort
        quickSort(array.clone());
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr) {

        int n = arr.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = arr[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int nL = left.length;
        int nR = right.length;
        int i = 0, j = 0, k = 0;
        while (i < nL && j < nR) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < nL) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nR) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return;
        }
        int pivot = arr[n - 1];
        int i = -1;
        for (int j = 0; j < n - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[n - 1];
        arr[n - 1] = temp;
        int[] left = new int[i + 1];
        int[] right = new int[n - i - 2];
        for (int k = 0; k <= i; k++) {
            left[k] = arr[k];
        }
        for (int k = i + 2; k < n; k++) {
            right[k - i - 2] = arr[k];
        }
        quickSort(left);
        quickSort(right);
        for (int k = 0; k <= i; k++) {
            arr[k] = left[k];
        }
        for (int k = i + 2; k < n; k++) {
            arr[k] = right[k - i - 2];
        }
        System.out.println(Arrays.toString(arr));
    }
}
