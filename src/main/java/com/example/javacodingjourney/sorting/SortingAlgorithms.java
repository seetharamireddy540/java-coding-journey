package com.example.javacodingjourney.sorting;

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

    }

    public static void quickSort(int[] arr) {
        // Implementation of Quick Sort
        // ...
    }
}
